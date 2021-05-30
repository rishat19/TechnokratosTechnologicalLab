package ru.kpfu.itis.ganiev.petchampionship.domain.model

data class Nomination(
    val id: String,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
