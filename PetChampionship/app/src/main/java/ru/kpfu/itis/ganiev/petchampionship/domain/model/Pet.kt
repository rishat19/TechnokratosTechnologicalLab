package ru.kpfu.itis.ganiev.petchampionship.domain.model

import org.joda.time.DateTime

data class Pet(
    val id: String ,
    val name: String,
    val ownerId: String,
    val ownerName: String,
    val nomination: Nomination,
    val positiveVotes: Int,
    val negativeVotes: Int,
    val publicationDate: DateTime
)