package ru.kpfu.itis.ganiev.petchampionship.di

import dagger.Subcomponent
import ru.kpfu.itis.ganiev.petchampionship.di.modules.ScreenModule
import ru.kpfu.itis.ganiev.petchampionship.presentation.authorization.AuthActivity
import ru.kpfu.itis.ganiev.petchampionship.presentation.authorization.AuthenticationFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list.BestPetsFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.profile.ProfileFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.signin.SignInFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.signup.SignUpFragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.voting.VotingFragment

@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

    fun inject(authFragment: AuthenticationFragment)

    fun inject(signInFragment: SignInFragment)

    fun inject(signUpFragment: SignUpFragment)

    fun inject(activity: AuthActivity)

    fun inject(profileFragment: ProfileFragment)

    fun inject(bestPetsFragment: BestPetsFragment)

    fun inject()

    fun inject(votingFragment: VotingFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ScreenComponent
    }
}