package ru.kpfu.itis.ganiev.petchampionship.presentation.di

import dagger.BindsInstance
import dagger.Subcomponent
import ru.kpfu.itis.ganiev.petchampionship.presentation.addpet.AddPetFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.authorization.AuthenticationFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.AuthActivity
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.details.PetDetailsFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list.BestPetsFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.voting.VotingFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.profile.ProfileFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import ru.kpfu.itis.ganiev.petchampionship.presentation.signin.SignInFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.signup.SignUpFragment

@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

    fun inject(votingFragment: VotingFragment)

    fun inject(authFragment: AuthenticationFragment)

    fun inject(signInFragment: SignInFragment)

    fun inject(signUpFragment: SignUpFragment)

    fun inject(activity: AuthActivity)

    fun inject(profileFragment: ProfileFragment)

    fun inject(bestPetsFragment: BestPetsFragment)

    fun inject(petsDetailsFragment: PetDetailsFragment)

    fun inject(addPetFragment: AddPetFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance router: Router
        ): ScreenComponent
    }
}
