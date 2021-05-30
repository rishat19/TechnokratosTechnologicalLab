package ru.kpfu.itis.ganiev.petchampionship.di.modules

import dagger.Module
import dagger.Provides
import ru.kpfu.itis.ganiev.petchampionship.data.network.RemoteApi
import ru.kpfu.itis.ganiev.petchampionship.data.repositories.RepositoryImpl
import ru.kpfu.itis.ganiev.petchampionship.data.room.PetsDao
import ru.kpfu.itis.ganiev.petchampionship.domain.interfaces.Repository
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideRepository(remoteApi: RemoteApi, petsDao: PetsDao): Repository =
        RepositoryImpl(remoteApi, petsDao)
}
