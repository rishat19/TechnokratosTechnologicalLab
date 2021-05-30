package ru.kpfu.itis.ganiev.petchampionship.data.network.model

import android.graphics.Bitmap
import com.google.firebase.firestore.Exclude
import ru.kpfu.itis.ganiev.petchampionship.domain.model.VoteStatus

data class PetRemote(
    @Exclude @get:Exclude
    var id: String = "",
    val name: String = "",
    var ownerId: String = "",
    var ownerName: String = "",
    val nominationId: String? = null,
    @Exclude @set:Exclude @get:Exclude
    var voteStatus: VoteStatus = VoteStatus.NOT_VOTE,
    @Exclude @set:Exclude @get:Exclude
    var positiveVotes: Int = 0,
    @Exclude @set:Exclude @get:Exclude
    var negativeVotes: Int = 0,
    val publicationTimestamp: Long = 0L,
    @Exclude @get:Exclude
    val image: Bitmap? = null,
    var imageUrl: String? = null
)
