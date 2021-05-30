package ru.kpfu.itis.ganiev.petchampionship.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.kpfu.itis.ganiev.petchampionship.data.room.PetsDao
import ru.kpfu.itis.ganiev.petchampionship.data.room.PetsDataBase
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideInstance(context: Context): PetsDataBase = Room.databaseBuilder(
        context,
        PetsDataBase::class.java,
        "pets"
    ).build()

    @Provides
    @Singleton
    fun provideDao(instance: PetsDataBase): PetsDao = instance.petsDao
}
