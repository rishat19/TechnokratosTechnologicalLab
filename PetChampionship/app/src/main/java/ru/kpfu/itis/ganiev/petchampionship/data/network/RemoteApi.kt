package ru.kpfu.itis.ganiev.petchampionship.data.network

import androidx.lifecycle.LiveData
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User

interface RemoteApi {
    suspend fun updateUsername(name: String)
    suspend fun signUp(user: User): User?
    suspend fun signIn(user: User): User?
    fun getCurrentUser(): User?
    suspend fun signOut()
    fun isUserAuthenticated(): LiveData<Boolean>
}