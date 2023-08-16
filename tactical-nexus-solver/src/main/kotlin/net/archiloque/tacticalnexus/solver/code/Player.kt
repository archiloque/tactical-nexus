package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.Position
import net.archiloque.tacticalnexus.solver.entities.Entity

class Player {

    companion object {
        fun playPosition(position: Position, tower: Tower) {
            var reachableEntityIndex = position.reachableEntities.nextSetBit(0)
            while ((reachableEntityIndex >= 0) && (reachableEntityIndex < tower.entitiesNumber)) {
                playPosition(reachableEntityIndex, position, tower)
                reachableEntityIndex = position.reachableEntities.nextSetBit(reachableEntityIndex + 1)
            }
        }

        private fun playPosition(entityIndex: Int, position: Position, tower: Tower) {
            val entity = tower.positionedEntities[entityIndex]
            when(entity.entity.getType()) {
                Entity.EntityType.Door -> TODO()
                Entity.EntityType.Enemy -> TODO()
                Entity.EntityType.Item -> TODO()
                Entity.EntityType.Key -> TODO()
                Entity.EntityType.PlayerStartPosition -> TODO()
                Entity.EntityType.Staircase -> TODO()
                Entity.EntityType.Wall -> {
                    throw IllegalStateException("Should not happen")
                }
            }
        }
    }
}