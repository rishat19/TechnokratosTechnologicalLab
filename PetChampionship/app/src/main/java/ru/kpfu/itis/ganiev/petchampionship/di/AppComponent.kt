package ru.kpfu.itis.ganiev.petchampionship.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.kpfu.itis.ganiev.petchampionship.di.modules.*
import ru.kpfu.itis.ganiev.petchampionship.presentation.di.ScreenComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        FirebaseModule::class,
        RepoModule::class,
        InteractorModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent {

    fun getScreenComponent(): ScreenComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun addApplication(application: Application): Builder

        fun create(): AppComponent
    }
}
