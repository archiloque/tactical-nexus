package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.Position

class Player {

    companion object {
        fun playPosition(position: Position, tower: Tower, positionSaver: PositionSaver) {
            var reachableEntityIndex = position.reachableEntities.nextSetBit(0)
            while ((reachableEntityIndex >= 0) && (reachableEntityIndex < tower.entitiesNumber)) {
                playPosition(reachableEntityIndex, position, tower, positionSaver)
                reachableEntityIndex = position.reachableEntities.nextSetBit(reachableEntityIndex + 1)
            }
        }

        private fun playPosition(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver) {
            val positionedEntity = tower.positionedEntities[entityIndex]
            val entity = positionedEntity.entity
            println(positionedEntity)
            entity.play(entityIndex, position, tower, positionSaver)
        }
    }
}
