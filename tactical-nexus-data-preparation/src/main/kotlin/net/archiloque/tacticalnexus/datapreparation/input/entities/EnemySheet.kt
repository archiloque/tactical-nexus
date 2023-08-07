package net.archiloque.tacticalnexus.datapreparation.input.entities

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.EnemyType

@Serializable
@SerialName("Enemy")
data class EnemySheet(
    @Required override val name: String,
    @Required @SerialName("lines") val enemies: List<Enemy>,
) : Sheet

@Serializable
data class Enemy(
    @Required @SerialName("Type") val type: EnemyType,
    @Required @SerialName("Level") val level: Int,
    @Required @SerialName("HP") val hp: Int,
    @Required @SerialName("ATK") val atk: Int,
    @Required @SerialName("DEF") val def: Int,
    @Required @SerialName("EXP") val exp: Int,
    @Required @SerialName("Drop") val drop: String,
)
