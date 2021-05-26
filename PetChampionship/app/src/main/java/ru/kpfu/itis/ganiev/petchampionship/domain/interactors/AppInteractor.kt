package ru.kpfu.itis.ganiev.petchampionship.domain.interactors

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User
import ru.kpfu.itis.ganiev.petchampionship.domain.interfaces.Repository
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.R

class AppInteractor(
    private val repository: Repository,
    private val context: Context
) {

    fun isUserAuthenticated(): LiveData<Boolean> = repository.isUserAuthenticated()

    suspend fun signIn(user: User) : Pair<Boolean, String?> {
        val userRes = repository.signIn(user)
        userRes?.let {
            return Pair(true, null)
        }
        return Pair(false, context.getString(R.string.unsuccessful_sign_up))
    }

    suspend fun signUp(user: User) : Pair<Boolean, String?>{
        val userRes = repository.signUp(user)
        Log.d("MYTAG", "UserUseCaseImpl sighUp(): ${userRes}")
        userRes?.let {
            return Pair(true, null)
        }
        return Pair(false, context.getString(R.string.unsuccessful_sign_up))
    }

    suspend fun updateUsername(name: String) = repository.updateUsername(name)

    fun getCurrentUser(): User? = repository.getCurrentUser()

    suspend fun signOut() = repository.signOut()
}