package ru.kpfu.itis.ganiev.petchampionship.data.network

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import ru.kpfu.itis.ganiev.petchampionship.data.network.model.NominationRemote
import ru.kpfu.itis.ganiev.petchampionship.data.network.model.PetRemote
import ru.kpfu.itis.ganiev.petchampionship.data.network.model.Vote
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User
import ru.kpfu.itis.ganiev.petchampionship.domain.model.VoteStatus
import java.io.ByteArrayOutputStream

private const val IMAGES_PATH = "images/"

class FirebaseRemoteApi(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: StorageReference
) : RemoteApi {

    private val petsRef = firestore.collection("pets")
    private val nominationsRef = firestore.collection("nominations")

    init {
        listenAuthState()
    }


    override suspend fun getNominations(): List<NominationRemote> =
        nominationsRef.get().await().documents.map {
            it.toObject(NominationRemote::class.java).also {
            } ?: throw ClassCastException("Incorrect PetRemote cast")
        }

    override suspend fun vote(petId: String, isPositive: Boolean): VoteStatus {
        val userId = getUserId() ?: throw IllegalStateException("Not authorized")
        val voteStatus: VoteStatus =
            if (isPositive) VoteStatus.POSITIVE_VOTE else VoteStatus.NEGATIVE_VOTE
        petsRef.document(petId).collection("votes").document(userId).set(
            Vote(
                userId = userId,
                voteStatus = voteStatus
            )
        )
        return voteStatus
    }

    override suspend fun addPet(pet: PetRemote) {
        pet.ownerId = auth.currentUser?.uid ?: throw IllegalStateException("Not authorized")
        pet.ownerName =
            auth.currentUser?.displayName ?: throw IllegalStateException("Not authorized")
        val petId = petsRef.add(pet).await().id
        pet.image?.let { image ->
            pet.imageUrl = uploadImage(petId, pet.image)
        }
        petsRef.document(petId).set(pet)
    }

    private suspend fun uploadImage(petId: String, bitmap: Bitmap): String? =
        suspendCancellableCoroutine { cor ->
            try {
                val baos = ByteArrayOutputStream()
                val path = IMAGES_PATH + petId
                var url: String? = null
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                storage.child(path)
                    .putBytes(baos.toByteArray())
                    .addOnSuccessListener {
                        storage.child(path).downloadUrl.addOnSuccessListener {
                            cor.resumeWith(Result.success(it.toString()))
                        }.addOnFailureListener {
                            cor.resumeWith(Result.failure(IllegalStateException("Getting user avatar url error")))
                        }
                    }.addOnFailureListener {
                        cor.resumeWith(Result.failure(IllegalStateException("Updating user avatar error")))
                    }
            } catch (ex: Exception) {
                cor.resumeWith(Result.failure(ex))
            }
        }

    private fun getUserId(): String? = auth.currentUser?.uid

    override suspend fun getPets(): List<PetRemote> =
        petsRef.get().await().documents.map { documentSnapshot ->
            val userId = getUserId() ?: throw IllegalStateException("Not authorized")
            val petId = documentSnapshot.id
            documentSnapshot.toObject(PetRemote::class.java)?.also {
                it.id = petId
                it.voteStatus = petsRef
                    .document(petId)
                    .collection("votes")
                    .document(userId).get().await()
                    .toObject(Vote::class.java)?.voteStatus ?: VoteStatus.NOT_VOTE
                it.positiveVotes = petsRef
                    .document(it.id)
                    .collection("votes").whereEqualTo("voteStatus", VoteStatus.POSITIVE_VOTE)
                    .get().await().documents.size
                it.negativeVotes = petsRef
                    .document(it.id)
                    .collection("votes").whereEqualTo("voteStatus", VoteStatus.NEGATIVE_VOTE)
                    .get().await().documents.size
            } ?: throw ClassCastException("Incorrect PetRemote cast")
        }


    private val isAuthenticated: MutableLiveData<Boolean> =
        MutableLiveData(auth.currentUser != null)

    override suspend fun updateUsername(name: String) {
        updateUserName(name)
    }

    override suspend fun signUp(user: User): User? {
        auth.createUserWithEmailAndPassword(
            user.email ?: throw IllegalArgumentException("Email must be not null"),
            user.password ?: throw IllegalArgumentException("Password must be not null")
        ).await()
        updateUserName(user.name)?.await()
        return auth.currentUser?.let {
            updateIsAuthenticatedState()
            getUserFromFirebaseUser(it)
        }
    }

    override fun isUserAuthenticated(): LiveData<Boolean> = isAuthenticated

    override fun getCurrentUser(): User? = auth.currentUser?.let {
        getUserFromFirebaseUser(it)
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    private fun getUserFromFirebaseUser(firebaseUser: FirebaseUser): User =
        with(firebaseUser) {
            User(
                id = uid,
                email = email,
                name = displayName
            )
        }

    override suspend fun signIn(user: User): User? {
        auth.signInWithEmailAndPassword(
            user.email ?: throw IllegalArgumentException("Email must be not null"),
            user.password ?: throw IllegalArgumentException("Password must be not null"),
        ).await()
        return auth.currentUser?.let {
            updateIsAuthenticatedState()
            getUserFromFirebaseUser(it)

        }
    }

    private fun updateIsAuthenticatedState() {
        isAuthenticated.postValue(isAuthenticated.value)
    }


    private fun listenAuthState() {
        auth.addAuthStateListener {
            isAuthenticated.postValue(it.currentUser != null)
        }
    }

    private fun updateUserName(name: String?): Task<Void>? {
        val updates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .build()
        return auth.currentUser?.updateProfile(updates)
    }

}
