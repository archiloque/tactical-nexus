package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

data class PositionedItem(val inputItem: net.archiloque.tacticalnexus.solver.entities.input.Item, val position: Position)

data class
ItemGroup(
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
        return items.map {PositionedDescription("Grab ${it.inputItem.name.lowercase()}", it.position) }.toTypedArray()
    }

    override fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
        newStates: MutableList<State>,
    ) {
        val newState = newState(entityIndex, state)
        apply(newState)
        addNewReachablePositions(
            entityIndex,
            newState,
            playableTower
        )
        newStates.add(newState)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemGroup

        return items.contentEquals(other.items)
    }

    override fun hashCode(): Int {
        return items.contentHashCode()
    }

}
