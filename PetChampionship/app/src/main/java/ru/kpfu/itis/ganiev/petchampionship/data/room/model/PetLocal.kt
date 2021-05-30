package ru.kpfu.itis.ganiev.petchampionship.data.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.joda.time.DateTime
import ru.kpfu.itis.ganiev.petchampionship.domain.model.VoteStatus

@Entity(
    foreignKeys = [ForeignKey(
        entity = NominationLocal::class,
        childColumns = ["nominationId"],
        parentColumns = ["id"],
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class PetLocal(
    @PrimaryKey
    val id: String,
    val petName: String,
    val ownerId: String,
    val ownerName: String,
    val voteStatus: VoteStatus,
    val nominationId: String?,
    val positiveVotes: Int,
    val negativeVotes: Int,
    val publicationDate: DateTime,
    val imageUrl: String? = null
)
