package ru.kpfu.itis.ganiev.petchampionship.data.repositories

import androidx.lifecycle.LiveData
import ru.kpfu.itis.ganiev.petchampionship.data.network.RemoteApi
import ru.kpfu.itis.ganiev.petchampionship.domain.interfaces.Repository
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User

class RepositoryImpl(
    private val remoteApi: RemoteApi
) : Repository {

    override suspend fun updateUsername(name: String) {
        remoteApi.updateUsername(name)
    }

    override suspend fun signUp(user: User): User? = remoteApi.signUp(user)

    override suspend fun signIn(user: User): User? = remoteApi.signIn(user)

    override fun getCurrentUser(): User? = remoteApi.getCurrentUser()

    override suspend fun signOut() = remoteApi.signOut()

    override fun isUserAuthenticated(): LiveData<Boolean> = remoteApi.isUserAuthenticated()

}