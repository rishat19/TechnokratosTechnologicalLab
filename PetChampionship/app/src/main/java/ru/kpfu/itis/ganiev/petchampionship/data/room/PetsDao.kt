package ru.kpfu.itis.ganiev.petchampionship.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.NominationLocal
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.PetLocal
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.relation.PetWithNomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.VoteStatus

@Dao
interface PetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePets(list: List<PetLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNominations(list: List<NominationLocal>)

    @Query("SELECT * FROM NominationLocal")
    suspend fun getNominationsTest(): List<NominationLocal>

    @Query("SELECT * FROM PetLocal")
    suspend fun getPestTest(): List<PetLocal>

    @Query("UPDATE PETLOCAL SET voteStatus = :voteStatus WHERE id = :petId")
    suspend fun updateVoteStatus(petId: String, voteStatus: VoteStatus)

    @Transaction
    @Query("SELECT * FROM PetLocal WHERE id = :petId LIMIT 1")
    fun getPetById(petId: String): PetWithNomination?

    @Transaction
    @Query("SELECT * FROM PetLocal")
    fun getPets(): LiveData<List<PetWithNomination>>

    @Query("SELECT * FROM NominationLocal")
    fun getNominations(): LiveData<List<NominationLocal>>
}
