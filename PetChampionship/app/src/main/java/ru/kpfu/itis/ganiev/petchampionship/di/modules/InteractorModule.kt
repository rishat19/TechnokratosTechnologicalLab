package ru.kpfu.itis.ganiev.petchampionship.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import ru.kpfu.itis.ganiev.petchampionship.domain.interfaces.Repository
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideUserInteractor(repository: Repository, context: Context): AppInteractor =
        AppInteractor(repository, context)
}
