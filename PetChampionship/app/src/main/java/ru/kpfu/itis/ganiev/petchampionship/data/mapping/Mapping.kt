package ru.kpfu.itis.ganiev.petchampionship.data.mapping

import org.joda.time.DateTime
import ru.kpfu.itis.ganiev.petchampionship.data.network.model.NominationRemote
import ru.kpfu.itis.ganiev.petchampionship.data.network.model.PetRemote
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.NominationLocal
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.PetLocal
import ru.kpfu.itis.ganiev.petchampionship.data.room.model.relation.PetWithNomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet

fun petWithNomToPet(pet: PetWithNomination): Pet =
    Pet(
        id = pet.pet.id,
        name = pet.pet.petName,
        ownerId = pet.pet.ownerId,
        ownerName = pet.pet.ownerName,
        nomination = nominationLocalToNomination(pet.nominationLocal),
        positiveVotes = pet.pet.positiveVotes,
        negativeVotes = pet.pet.negativeVotes,
        publicationDate = pet.pet.publicationDate,
        imageUrl = pet.pet.imageUrl
    )

fun petToPetRemote(pet: Pet): PetRemote =
    PetRemote(
        id = pet.id,
        name = pet.name,
        ownerId = pet.ownerId,
        ownerName = pet.ownerName,
        nominationId = pet.nomination.id,
        voteStatus = pet.voteStatus,
        positiveVotes = pet.positiveVotes,
        negativeVotes = pet.negativeVotes,
        publicationTimestamp = pet.publicationDate.millis,
        image = pet.image
    )

fun nominationLocalToNomination(nomination: NominationLocal): Nomination =
    Nomination(
        id = nomination.id,
        name = nomination.name
    )

fun petRemoteToPetLocal(pet: PetRemote): PetLocal =
    PetLocal(
        id = pet.id,
        petName = pet.name,
        ownerId = pet.ownerId,
        ownerName = pet.ownerName,
        nominationId = pet.nominationId,
        voteStatus = pet.voteStatus,
        positiveVotes = pet.positiveVotes,
        negativeVotes = pet.negativeVotes,
        publicationDate = DateTime(pet.publicationTimestamp),
        imageUrl = pet.imageUrl
    )

fun nominationRemoteToNominationLocal(nomination: NominationRemote): NominationLocal =
    NominationLocal(
        id = nomination.id,
        name = nomination.name
    )
