package ru.kpfu.itis.ganiev.petchampionship.presentation.common

import androidx.lifecycle.ViewModel
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val appUseCase: AppInteractor,
    private val router: Router
) : ViewModel() {

    init {
        appUseCase.isUserAuthenticated().observeForever() {
            if (it) {
                router.navigateToProfile()
            } else {
                router.navigateToAuthentication()
            }
        }
    }

}
