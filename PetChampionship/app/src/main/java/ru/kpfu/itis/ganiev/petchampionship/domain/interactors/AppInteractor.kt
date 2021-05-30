package ru.kpfu.itis.ganiev.petchampionship.domain.interactors

import android.content.Context
import android.util.Pair
import androidx.lifecycle.LiveData
import ru.kpfu.itis.ganiev.petchampionship.domain.interfaces.Repository
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.R

class AppInteractor(
    private val repository: Repository,
    private val context: Context
) {

    fun isUserAuthenticated(): LiveData<Boolean> = repository.isUserAuthenticated()

    suspend fun signIn(user: User): Pair<Boolean, String?> {
        val userRes = repository.signIn(user)
        userRes?.let {
            return Pair(true, null)
        }
        return Pair(false, context.getString(R.string.unsuccessful_sign_up))
    }

    suspend fun signUp(user: User): Pair<Boolean, String?> {
        val userRes = repository.signUp(user)
        userRes?.let {
            return Pair(true, null)
        }
        return Pair(false, context.getString(R.string.unsuccessful_sign_up))
    }

    suspend fun updateUsername(name: String) = repository.updateUsername(name)

    suspend fun refreshData() = repository.refreshData()

    fun getNominations(): LiveData<List<Nomination>> = repository.getNominations()

    fun getPets(): LiveData<List<Pet>> = repository.getPets()

    fun getCurrentUser(): User? = repository.getCurrentUser()

    suspend fun getPetById(petId: String): Pet? = repository.getPetById(petId)

    suspend fun signOut() = repository.signOut()

    suspend fun vote(petId: String, isPositive: Boolean) = repository.vote(petId, isPositive)

    suspend fun addPet(pet: Pet) = repository.addPet(pet)
}
