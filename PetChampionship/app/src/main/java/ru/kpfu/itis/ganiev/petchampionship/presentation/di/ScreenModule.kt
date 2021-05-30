package ru.kpfu.itis.ganiev.petchampionship.presentation.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.kpfu.itis.ganiev.petchampionship.presentation.addpet.AddPetViewModel
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.AuthorizationViewModel
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.details.PetDetailsViewModel
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list.PetsViewModel
import ru.kpfu.itis.ganiev.petchampionship.presentation.profile.ProfileViewModel
import ru.kpfu.itis.ganiev.petchampionship.presentation.signin.SignInViewModel
import ru.kpfu.itis.ganiev.petchampionship.presentation.signup.SignUpViewModel

@Module
interface ScreenModule {
    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun provideSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun provideSignInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    fun provideAuthorizeViewModel(authorizationViewModel: AuthorizationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PetsViewModel::class)
    fun provideBestPetsViewModel(petsViewModel: PetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PetDetailsViewModel::class)
    fun providePetDetailsViewMode(bestPetsViewModel: PetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddPetViewModel::class)
    fun provideAddPetVM(addPetViewModel: AddPetViewModel): ViewModel

}
