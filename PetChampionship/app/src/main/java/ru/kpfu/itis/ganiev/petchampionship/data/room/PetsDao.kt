package ru.kpfu.itis.ganiev.petchampionship.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PetsDao {

//    @Transaction
//    @Query("SELECT * FROM PetLocal")
//    fun getPets(): LiveData<List<PetLocalWithNomination>>
}