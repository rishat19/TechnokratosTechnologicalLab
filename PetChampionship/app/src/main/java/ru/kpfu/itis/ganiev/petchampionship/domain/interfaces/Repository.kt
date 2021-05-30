package ru.kpfu.itis.ganiev.petchampionship.domain.interfaces

import androidx.lifecycle.LiveData
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User

interface Repository {
    suspend fun updateUsername(name: String)
    suspend fun signUp(user: User): User?
    suspend fun signIn(user: User): User?
    fun getCurrentUser(): User?
    suspend fun signOut()
    fun isUserAuthenticated(): LiveData<Boolean>
    fun getPets(): LiveData<List<Pet>>
    suspend fun getPetById(petId: String): Pet?
    fun getNominations(): LiveData<List<Nomination>>
    suspend fun refreshData()
    suspend fun vote(petId: String, isPositive: Boolean)
    suspend fun addPet(pet: Pet)
}
