package ru.kpfu.itis.ganiev.petchampionship.data.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.joda.time.DateTime
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination

@Entity(
//    foreignKeys = [ForeignKey(
//        entity = NominationLocal::class,
//        childColumns = ["nominationId"],
//        parentColumns = ["id"]
//    )]
)
data class PetLocal(
    @PrimaryKey
    val id: String,
    val petName: String,
    val ownerId: String,
    val ownerName: String,
    val nominationId: String,
    val positiveVotes: Int,
    val negativeVotes: Int,
 //   val publicationDate: DateTime
)