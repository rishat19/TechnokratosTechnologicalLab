package ru.kpfu.itis.ganiev.petchampionship.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.NominationLocal
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.PetLocal

@Database(
    entities = [
        PetLocal::class,
        NominationLocal::class
    ],
    version = 1,
    exportSchema = false
)
//@TypeConverters(DateTimeConverter::class)
abstract class PetsDataBase : RoomDatabase() {
    abstract val petsDao: PetsDao
}