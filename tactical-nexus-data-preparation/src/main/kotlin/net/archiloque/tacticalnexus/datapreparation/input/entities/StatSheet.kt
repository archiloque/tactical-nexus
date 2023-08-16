package net.archiloque.tacticalnexus.datapreparation.input.entities

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Stat")
data class StatSheet(
    @Required override val name: String,
    @Required @SerialName("lines") val stats: List<Stat>,
) : Sheet

@Serializable
data class Stat(
    @Required @SerialName("HP") val hp: Int,
    @Required @SerialName("ATK") val atk: Int,
    @Required @SerialName("DEF") val def: Int,
    @Required @SerialName("Tower") val tower: Int,
)
