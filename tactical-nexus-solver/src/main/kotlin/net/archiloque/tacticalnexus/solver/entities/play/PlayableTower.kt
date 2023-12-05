package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Level

class PlayableTower(
    val entitiesCount: Int,
    val playEntities: Array<PlayEntity>,
    val startingPositionPosition: Position,
    val reachableByStartingPosition: IntArray,
    val reachable: Array<IntArray>,
    val roomsSingleDoor: IntArray,
    val checkScorePosition: Int?,
    val starScorePosition: Int?,
    val crownScorePosition: Int?,
    val levels: Array<Level>,
) {
    fun printAll() {
        for (entityIndex in 0..<entitiesCount) {
            val playEntity = playEntities[entityIndex]
            val reachableByEntity = reachable[entityIndex]
            val canReach = if (reachableByEntity.isEmpty()) {
                "nothing"
            } else {
                reachableByEntity.joinToString(", ")
            }
            println("$entityIndex $playEntity can reach $canReach")
        }
    }
}
