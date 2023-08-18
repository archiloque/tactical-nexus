package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.entities.Entity

data class PositionedEntity(
    val entity: Entity,
    val position: Position,
)