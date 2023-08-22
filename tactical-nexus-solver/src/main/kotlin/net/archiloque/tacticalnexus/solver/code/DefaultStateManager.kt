package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import net.archiloque.tacticalnexus.solver.entities.Door
import net.archiloque.tacticalnexus.solver.entities.Enemy
import net.archiloque.tacticalnexus.solver.entities.Entity
import net.archiloque.tacticalnexus.solver.entities.Item
import net.archiloque.tacticalnexus.solver.entities.Key
import net.archiloque.tacticalnexus.solver.entities.Tower
import org.ktorm.database.Database
import org.ktorm.support.postgresql.bulkInsertOrUpdate

class DefaultStateManager(
    val database: Database,
    val tower: Tower,
    val playableTower: PlayableTower,
    val initialState: State,
) :
    StateManager {

    override fun save(state: State) {
        database.bulkInsertOrUpdate(States) {
            item {
                set(it.status, StateStatus.new)

                set(it.visited, state.visited)
                set(it.reachable, state.reachable)

                set(it.atk, state.atk)
                set(it.def, state.def)
                set(it.exp, state.exp)
                set(it.hp, state.hp)

                set(it.expBonus, state.expBonus)
                set(it.hpBonus, state.hpBonus)

                set(it.blue_keys, state.blueKeys)
                set(it.crimson_keys, state.crimsonKeys)
                set(it.platinum_keys, state.platinumKeys)
                set(it.violet_keys, state.violetKeys)
                set(it.yellow_keys, state.yellowKeys)

                set(it.moves, state.moves)
            }
            onConflict(
                States.visited,
                States.reachable,

                States.atk,
                States.def,
                States.exp,
                States.hp,

                States.expBonus,
                States.hpBonus,

                States.blue_keys,
                States.crimson_keys,
                States.platinum_keys,
                States.violet_keys,
                States.yellow_keys,
            ) {
                doNothing()
            }
        }
    }

    override fun reachedExit(state: State) {
        println("Win !")
        println(state)
        val currentState = initialState.copy()

        val moveIndexLength = state.moves.size.toString().length
        val positionedEntities = state.moves.map { playableTower.positionedEntities[it] }
        val levelLength = positionedEntities.map { it.position.level }.max().toString().length
        val columnLength = positionedEntities.map { it.position.column }.max().toString().length
        val lineLength = positionedEntities.map { it.position.line }.max().toString().length
        var index = 1
        val initialPosition = playableTower.positionedEntities[playableTower.startingPosition].position
        print(
            0,
            moveIndexLength,
            initialPosition,
            initialPosition,
            levelLength,
            lineLength,
            columnLength,
            "Starting",
            currentState
        )
        var lastPosition: Position = initialPosition
        for (positionedEntity in positionedEntities) {
            val entity = positionedEntity.entity
            val position = positionedEntity.position
            val description = description(entity, currentState)
            if (description != null) {
                print(
                    index,
                    moveIndexLength,
                    position,
                    lastPosition,
                    levelLength,
                    lineLength,
                    columnLength,
                    description,
                    currentState
                )
                index++
                lastPosition = position
            }
        }
    }

    private fun print(
        index: Int,
        moveIndexLength: Int,
        currentPosition: Position,
        previousPosition: Position,
        levelLength: Int,
        lineLength: Int,
        columnLength: Int,
        description: String,
        currentState: State,
    ) {
        println(
            "${index.toString().padStart(moveIndexLength)} (${
                currentPosition.level.toString().padStart(levelLength)
            }, ${currentPosition.line.toString().padStart(lineLength)}, ${
                currentPosition.column.toString().padStart(columnLength)
            }) ${description}"
        )
        println(
            "Hp: ${currentState.hp}, Atk: ${currentState.atk}, Def: ${currentState.def}, Exp: ${currentState.exp}, Exp bonus: ${currentState.expBonus}, HP bonus: ${currentState.hpBonus}"
        )
        printMove(currentPosition, previousPosition, tower)
        println()
    }

    private fun description(
        entity: Entity,
        state: State,
    ): String? {
        return when (entity.getType()) {
            Entity.EntityType.Door -> {
                val door = entity as Door
                door.apply(state)
                "Open ${door.color} door"
            }

            Entity.EntityType.Enemy -> {
                val enemy = entity as Enemy
                val lostHp = enemy.apply(state)
                "Fight lv ${enemy.level} ${enemy.type}"
            }

            Entity.EntityType.Exit -> {
                "Take exit"
            }

            Entity.EntityType.Item -> {
                val item = entity as Item
                item.apply(state)
                "Grab ${item.name.lowercase()}"
            }

            Entity.EntityType.Key -> {
                val key = entity as Key
                key.apply(state)
                "Grab ${key.color} key"
            }

            Entity.EntityType.PlayerStartPosition -> {
                throw IllegalStateException("Should not happen")
            }

            Entity.EntityType.Staircase -> {
                null
            }

            Entity.EntityType.Wall -> {
                throw IllegalStateException("Should not happen")
            }
        }
    }

    private fun printMove(currentPosition: Position, previousPosition: Position, tower: Tower) {
        val level = tower.levels()[currentPosition.level]
        for ((lineIndex, line) in level.entities.withIndex()) {
            println(line.mapIndexed { columnIndex, entity ->
                if ((lineIndex == currentPosition.line) && (columnIndex == currentPosition.column)) {
                    'X'
                } else if ((currentPosition.level == previousPosition.level) && (lineIndex == previousPosition.line) && (columnIndex == previousPosition.column)) {
                    '□'
                } else if ((entity != null) && (entity.getType() == Entity.EntityType.Wall)) {
                    '#'
                } else {
                    ' '
                }
            }.joinToString(""))
        }
    }
}