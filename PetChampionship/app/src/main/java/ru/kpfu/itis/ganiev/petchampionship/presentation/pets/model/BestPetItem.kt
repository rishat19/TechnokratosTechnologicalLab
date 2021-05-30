package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.model

import org.joda.time.format.DateTimeFormat
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet

data class BestPetItem(
    val id: String,
    val name: String,
    val ownerName: String,
    val publicationDate: String,
    val positiveVotes: String,
    val negativeVotes: String,
    val nominationName: String,
    val nominationId: String,
    val imageUrl: String? = null
) {
    companion object {
        fun fromPet(pet: Pet): BestPetItem {
            return BestPetItem(
                id = pet.id,
                name = pet.name,
                negativeVotes = pet.negativeVotes.toString(),
                ownerName = pet.ownerName,
                positiveVotes = pet.positiveVotes.toString(),
                publicationDate = pet.publicationDate.toString(DateTimeFormat.shortDateTime()),
                nominationId = pet.nomination.id,
                nominationName = pet.nomination.name,
                imageUrl = pet.imageUrl
            )
        }
    }
}
