package ru.kpfu.itis.ganiev.petchampionship.data.room.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.NominationLocal
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.PetLocal

data class PetWithNomination(
    @Embedded
    var pet: PetLocal,
    @Relation(
        parentColumn = "nominationId",
        entityColumn = "id",
        entity = NominationLocal::class
    )
    var nominationLocal: NominationLocal
)
