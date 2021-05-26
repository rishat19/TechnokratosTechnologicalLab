package ru.kpfu.itis.ganiev.petchampionship.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProfileViewModel @Inject constructor(
    private val appInteract: AppInteractor,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val userLiveData: MutableLiveData<User?> = MutableLiveData()

    fun signOut() {
        viewModelScope.launch(coroutineContext) {
            appInteract.signOut()
            getCurrentUser()
        }
    }

    fun getCurrentUser(): LiveData<User?> {
        viewModelScope.launch(coroutineContext) {
            userLiveData.postValue(appInteract.getCurrentUser())
        }
        return userLiveData
    }

    fun updateName(name: String) {
        viewModelScope.launch(coroutineContext) {
            appInteract.updateUsername(name)
        }
    }
}