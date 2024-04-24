package net.archiloque.tacticalnexus.datapreparation.output.game

import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.enums.EnemyType

@Serializable
data class Enemy(
    val type: EnemyType,
    val level: Int,
    val name: String,

    val atk: Int,
    val def: Int,
    val drop: String,
    val exp: Int,
    val hp: Int,
) {
    companion object {
        fun fromInput(input: net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy) : Enemy {
            return Enemy(
                input.type,
                input.level,
                "",

                input.atk,
                input.def,
                input.drop,
                input.exp,
                input.hp
            )
        }
    }
}