package ru.kpfu.itis.ganiev.petchampionship.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SignInViewModel @Inject constructor(
    private val appUseCase: AppInteractor,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val errorSignInLiveData: MutableLiveData<String> = MutableLiveData()
    fun getSignInErrorLiveData(): LiveData<String> = errorSignInLiveData

    fun authorize(email: String, password: String) {
        viewModelScope.launch(coroutineContext) {
            try {
                val isSuccessful = appUseCase.signIn(
                    User(
                        email = email,
                        password = password
                    )
                )
                if (!isSuccessful.first) errorSignInLiveData.postValue(isSuccessful.second)
            } catch (e: Exception) {
                e.printStackTrace()
                errorSignInLiveData.postValue(e.message)
            }
        }
    }
}