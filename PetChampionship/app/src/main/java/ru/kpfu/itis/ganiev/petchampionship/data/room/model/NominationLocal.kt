package ru.kpfu.itis.ganiev.petchampionship.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NominationLocal(
    @PrimaryKey
    val id: String,
    val name: String
)