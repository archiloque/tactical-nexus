package net.archiloque.tacticalnexus.datapreparation.output.game

import kotlinx.serialization.Serializable

@Serializable
data class Enemy(
    val type: String,
    val level: Int,
    val name: String,

    val atk: Int,
    val def: Int,
    val drop: String,
    val exp: Int,
    val hp: Int,
) {
    companion object {
        fun fromInput(input: net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy): Enemy {
            val drop = input.drop.replace('_', ' ').replaceFirstChar { it.uppercase() }
            return Enemy(
                input.type.name.replaceFirstChar { it.uppercase() },
                input.level,
                input.name,

                input.atk,
                input.def,
                drop,
                input.exp,
                input.hp
            )
        }
    }
}