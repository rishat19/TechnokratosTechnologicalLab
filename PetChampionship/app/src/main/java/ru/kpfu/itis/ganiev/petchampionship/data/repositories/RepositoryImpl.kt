package ru.kpfu.itis.ganiev.petchampionship.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.kpfu.itis.ganiev.petchampionship.data.mapping.*
import ru.kpfu.itis.ganiev.petchampionship.data.network.RemoteApi
import ru.kpfu.itis.ganiev.petchampionship.data.room.PetsDao
import ru.kpfu.itis.ganiev.petchampionship.domain.interfaces.Repository
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User

class RepositoryImpl(
    private val remoteApi: RemoteApi,
    private val petsDao: PetsDao
) : Repository {

    override suspend fun updateUsername(name: String) {
        remoteApi.updateUsername(name)

    }

    override suspend fun signUp(user: User): User? = remoteApi.signUp(user)

    override suspend fun signIn(user: User): User? = remoteApi.signIn(user)

    override fun getCurrentUser(): User? = remoteApi.getCurrentUser()

    override suspend fun signOut() = remoteApi.signOut()

    override fun isUserAuthenticated(): LiveData<Boolean> = remoteApi.isUserAuthenticated()

    override fun getPets(): LiveData<List<Pet>> {
        return petsDao.getPets().map {
            it.map { pet ->
                petWithNomToPet(pet)
            }
        }
    }

    override suspend fun getPetById(petId: String): Pet? {
        return petsDao.getPetById(petId)?.let {
            petWithNomToPet(it)
        }
    }

    override suspend fun refreshData() {
        remoteApi.getNominations().also {
            petsDao.saveNominations(it.map(::nominationRemoteToNominationLocal))
        }
        remoteApi.getPets().also {
            petsDao.savePets(it.map(::petRemoteToPetLocal))
        }
    }

    override suspend fun vote(petId: String, isPositive: Boolean) {
        val status = remoteApi.vote(petId, isPositive)
        petsDao.updateVoteStatus(petId, status)
    }

    override suspend fun addPet(pet: Pet) = remoteApi.addPet(petToPetRemote(pet))

    override fun getNominations(): LiveData<List<Nomination>> {
        return petsDao.getNominations().map { list ->
            list.map {
                nominationLocalToNomination(it)
            }
        }
    }

}
