package ru.kpfu.itis.ganiev.petchampionship.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Provides
    fun provideCoroutineContext(): CoroutineContext = SupervisorJob() + Dispatchers.IO

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

}
