package ru.kpfu.itis.ganiev.petchampionship.di.modules

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import ru.kpfu.itis.ganiev.petchampionship.data.network.RemoteApi
import ru.kpfu.itis.ganiev.petchampionship.data.repositories.RepositoryImpl
import ru.kpfu.itis.ganiev.petchampionship.domain.interfaces.Repository
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideRepository(remoteApi: RemoteApi): Repository = RepositoryImpl(remoteApi)
}