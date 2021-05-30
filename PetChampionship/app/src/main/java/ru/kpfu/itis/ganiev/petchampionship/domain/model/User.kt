package ru.kpfu.itis.ganiev.petchampionship.domain.model

import androidx.room.PrimaryKey

data class User(
    @PrimaryKey
    var id: String? = null,
    var email: String? = null,
    var name: String? = null,
    var password: String? = null,
)
