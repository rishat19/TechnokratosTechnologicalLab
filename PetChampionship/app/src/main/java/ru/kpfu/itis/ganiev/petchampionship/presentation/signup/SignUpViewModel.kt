package ru.kpfu.itis.ganiev.petchampionship.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SignUpViewModel @Inject constructor(
    private val appInteractor: AppInteractor,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val errorSignUpLiveData: MutableLiveData<String> = MutableLiveData()
    fun getSignUpErrorLiveData(): LiveData<String> = errorSignUpLiveData

    fun signUp(email: String, name: String, password: String) {
        viewModelScope.launch(coroutineContext) {
            try {
                val isSuccessful = appInteractor.signUp(User(null, email, name, password))
                if (!isSuccessful.first) errorSignUpLiveData.postValue(isSuccessful.second)
            } catch (e: Exception) {
                e.printStackTrace()
                errorSignUpLiveData.postValue(e.message)
            }
        }
    }
}