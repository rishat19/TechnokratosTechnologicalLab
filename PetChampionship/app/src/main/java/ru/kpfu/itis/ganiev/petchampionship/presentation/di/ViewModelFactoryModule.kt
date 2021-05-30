package ru.kpfu.itis.ganiev.petchampionship.presentation.di

import androidx.lifecycle.ViewModelProvider
import com.example.gargabesorter.utils.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun provideViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}
