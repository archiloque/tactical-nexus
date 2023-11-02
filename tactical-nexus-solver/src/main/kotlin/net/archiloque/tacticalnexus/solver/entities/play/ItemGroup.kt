package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

data class PositionedItem(
    val inputItem: net.archiloque.tacticalnexus.solver.entities.input.Item,
    val position: Position,
) {
    override fun toString(): String {
        return "${inputItem.name} at $position"
    }
}

class ItemGroup(
    private val items: Array<PositionedItem>,
) : Item(
    items.sumOf { it.inputItem.atk },
    items.sumOf { it.inputItem.def },
    items.sumOf { it.inputItem.expBonus },
    items.sumOf { it.inputItem.hp },
    items.sumOf { it.inputItem.hpBonus },
), PlayEntity {

    private val positions = items.map { it.position }.toTypedArray()

    override fun getType(): PlayEntityType {
        return PlayEntityType.ItemGroup
    }

    override fun getPositions(): Array<Position> {
        return positions
    }

    override fun description(): Array<PositionedDescription> {
        return items.map { PositionedDescription("Grab the ${it.inputItem.name.lowercase()}", it.position) }
            .toTypedArray()
    }

    override fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        val newState = newState(entityIndex, state)
        apply(newState)
        addNewReachablePositions(
            entityIndex,
            newState,
            playableTower
        )
        stateManager.save(newState)
    }

    override fun toString(): String {
        return "Item group: ${items.map { it.toString() }.joinToString(", ")}"
    }
}
