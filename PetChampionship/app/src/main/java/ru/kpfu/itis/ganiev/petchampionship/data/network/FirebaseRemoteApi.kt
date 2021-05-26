package ru.kpfu.itis.ganiev.petchampionship.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User

class FirebaseRemoteApi(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : RemoteApi {

    init {
        listenAuthState()
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

    private fun updateIsAuthenticatedState(){
        isAuthenticated.postValue(isAuthenticated.value)
    }


    private fun listenAuthState() {
        auth.addAuthStateListener {
            isAuthenticated.postValue(auth.currentUser != null)
        }
    }

    private fun updateUserName(name: String?): Task<Void>? {
        val updates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .build()
        return auth.currentUser?.updateProfile(updates)
    }


}