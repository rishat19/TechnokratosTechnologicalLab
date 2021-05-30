package ru.kpfu.itis.ganiev.petchampionship.domain.model

import android.graphics.Bitmap
import org.joda.time.DateTime

data class Pet(
    val id: String = "",
    val name: String,
    val ownerId: String = "",
    val ownerName: String = "",
    val nomination: Nomination,
    val voteStatus: VoteStatus = VoteStatus.NOT_VOTE,
    val positiveVotes: Int = 0,
    val negativeVotes: Int = 0,
    val publicationDate: DateTime,
    val image: Bitmap? = null,
    val imageUrl: String? = null
)

enum class VoteStatus(name: String) {
    POSITIVE_VOTE("positive"),
    NEGATIVE_VOTE("negative"),
    NOT_VOTE("not_voted")
}
