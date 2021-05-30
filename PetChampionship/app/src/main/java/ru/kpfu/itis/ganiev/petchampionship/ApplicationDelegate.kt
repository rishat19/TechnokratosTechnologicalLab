package ru.kpfu.itis.ganiev.petchampionship

import android.app.Application
import ru.kpfu.itis.ganiev.petchampionship.di.AppComponent
import ru.kpfu.itis.ganiev.petchampionship.di.DaggerAppComponent
import ru.kpfu.itis.ganiev.petchampionship.presentation.di.ScreenComponent
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router

class ApplicationDelegate : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .addApplication(this)
            .create()
    }

    companion object {
        lateinit var appComponent: AppComponent
        fun screenComponent(): ScreenComponent.Factory =
            appComponent.getScreenComponent()
    }
}
