package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PositionSaver
import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.Position

data class Item(
    val name: String,
    val atk: Int,
    val def: Int,
    val hp: Int,
) : Entity() {
    override fun getType(): EntityType {
        return EntityType.Item
    }

    public fun collect(position: Position) {
        position.atk += atk
        position.def += def
        position.hp += hp
    }

    override fun play(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver) {
        val newPosition = newPosition(entityIndex, position)
        collect(newPosition)
        addNewPositions(
            entityIndex,
            newPosition,
            tower,
            positionSaver
        )
    }
}