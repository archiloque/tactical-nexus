package net.archiloque.tacticalnexus.datapreparation.output.game

import kotlinx.serialization.Serializable

@Serializable
data class Tower(
    val enemies: List<Enemy>,
) {
    companion object {



    }
}
