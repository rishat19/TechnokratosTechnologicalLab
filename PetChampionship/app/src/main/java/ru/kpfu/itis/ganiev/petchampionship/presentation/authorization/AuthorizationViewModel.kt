package ru.kpfu.itis.ganiev.petchampionship.presentation.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val appUseCase: AppInteractor
) : ViewModel() {

    fun isUserAuthenticated(): LiveData<Boolean> = appUseCase.isUserAuthenticated()

}