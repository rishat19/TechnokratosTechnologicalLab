package ru.kpfu.itis.ganiev.petchampionship.data.network

import androidx.lifecycle.LiveData
import ru.kpfu.itis.ganiev.petchampionship.data.network.model.NominationRemote
import ru.kpfu.itis.ganiev.petchampionship.data.network.model.PetRemote
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User
import ru.kpfu.itis.ganiev.petchampionship.domain.model.VoteStatus

interface RemoteApi {
    suspend fun updateUsername(name: String)
    suspend fun signUp(user: User): User?
    suspend fun signIn(user: User): User?
    fun getCurrentUser(): User?
    suspend fun signOut()
    fun isUserAuthenticated(): LiveData<Boolean>
    suspend fun getPets(): List<PetRemote>
    suspend fun getNominations(): List<NominationRemote>
    suspend fun vote(petId: String, isPositive: Boolean): VoteStatus
    suspend fun addPet(pet: PetRemote)
}
