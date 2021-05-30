package ru.kpfu.itis.ganiev.petchampionship.data.network.model

import ru.kpfu.itis.ganiev.petchampionship.domain.model.VoteStatus

data class Vote(
    val userId: String = "",
    val voteStatus: VoteStatus = VoteStatus.NOT_VOTE
)
