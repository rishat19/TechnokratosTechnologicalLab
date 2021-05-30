package ru.kpfu.itis.ganiev.petchampionship.presentation.router

interface Router {
    fun navigateToSignInFragment()

    fun navigateToSignUpFragment()

    fun navigateToAuthentication()

    fun navigateToProfile()

    fun navigateBestPetsToPetsDetails(petId: String)

    fun back()
    fun navigateToAddPet()
}
