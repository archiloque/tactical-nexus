package net.archiloque.tacticalnexus.solver.code

import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import net.archiloque.tacticalnexus.solver.entities.Door
import net.archiloque.tacticalnexus.solver.entities.Enemy
import net.archiloque.tacticalnexus.solver.entities.Entity
import net.archiloque.tacticalnexus.solver.entities.Item
import net.archiloque.tacticalnexus.solver.entities.Key
import net.archiloque.tacticalnexus.solver.entities.LevelUp
import net.archiloque.tacticalnexus.solver.entities.LevelUpType
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

    override fun save(states: List<State>) {
        if (states.isNotEmpty()) {
            database.bulkInsertOrUpdate(States) {
                for (state in states) {
                    item {
                        set(it.status, StateStatus.new)

                        set(it.atk, state.atk)
                        set(it.def, state.def)
                        set(it.exp, state.exp)
                        set(it.hp, state.hp)

                        set(it.visited, state.visited)
                        set(it.reachable, state.reachable)

                        set(it.expBonus, state.expBonus)
                        set(it.hpBonus, state.hpBonus)

                        set(it.blue_keys, state.blueKeys)
                        set(it.crimson_keys, state.crimsonKeys)
                        set(it.platinum_keys, state.platinumKeys)
                        set(it.violet_keys, state.violetKeys)
                        set(it.yellow_keys, state.yellowKeys)

                        set(it.moves, state.moves)
                    }
                }
                onConflict(
                    States.atk,
                    States.def,
                    States.exp,
                    States.hp,

                    States.visited,
                    States.reachable,

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
    }

    override fun reachedExit(finalState: State) {
        synchronized(this) {
            println(finalState)
            val currentState = initialState.copy()

            val moveIndexLength = finalState.moves.size.toString().length
            val positionedEntities = finalState.moves.filter { it >= 0 }.map { playableTower.positionedEntities[it] }
            val maxLevelIndexLength = positionedEntities.map { it.position.level }.max().toString().length
            val maxLevelColumnsLength = positionedEntities.map { it.position.column }.max().toString().length
            val maxLevelLinesLength = positionedEntities.map { it.position.line }.max().toString().length
            var index = 1
            val initialPosition = playableTower.positionedEntities[playableTower.startingPosition].position
            var currentLevelUpIndex = 0
            printStatus(
                0,
                moveIndexLength,
                initialPosition,
                maxLevelIndexLength,
                maxLevelLinesLength,
                maxLevelColumnsLength,
                "Starting",
                currentState,
                currentLevelUpIndex,
            )
            printMove(initialPosition, initialPosition, tower)
            println()
            var lastPosition: Position = initialPosition
            for (move in finalState.moves) {
                if (move >= 0) {
                    val positionedEntity = playableTower.positionedEntities[move]
                    val entity = positionedEntity.entity
                    val position = positionedEntity.position
                    val description = apply(entity, currentState)
                    if (description != null) {
                        printStatus(
                            index,
                            moveIndexLength,
                            position,
                            maxLevelIndexLength,
                            maxLevelLinesLength,
                            maxLevelColumnsLength,
                            description,
                            currentState,
                            currentLevelUpIndex
                        )
                        printMove(position, lastPosition, tower)
                        println()

                        lastPosition = position
                    }
                } else {
                    currentLevelUpIndex++
                    val levelUp = LevelUp.levelUp(currentState.exp)
                    val levelUpType = LevelUpType.entries.find { it.type == move }!!
                    val description = when (levelUpType) {
                        LevelUpType.atk -> {
                            "gain ${levelUp.atk} atk"
                        }

                        LevelUpType.def -> {
                            "gain ${levelUp.def} def"
                        }

                        LevelUpType.blueKeys -> {
                            "gain 2 blue key(s)"
                        }

                        LevelUpType.crimsonKeys -> {
                            "gain 1 crimson key(s)"
                        }

                        LevelUpType.yellowKeys -> {
                            "gain 3 yellow key(s)"
                        }
                    }
                    Enemy.applyLevelUp(levelUpType, currentState, levelUp)
                    printStatus(
                        index,
                        moveIndexLength,
                        lastPosition,
                        maxLevelIndexLength,
                        maxLevelLinesLength,
                        maxLevelColumnsLength,
                        "Level up to ${currentLevelUpIndex}, ${description}",
                        currentState,
                        currentLevelUpIndex,
                    )
                    println()
                }
                index++
            }
            exitProcess(0)
        }
    }

    private fun printStatus(
        index: Int,
        moveIndexLength: Int,
        currentPosition: Position,
        maxLevelIndexLength: Int,
        maxLevelLinesLength: Int,
        maxLevelColumnsLength: Int,
        moveDescription: String,
        currentState: State,
        currentLevelUpIndex: Int,
    ) {
        println(
            "${index.toString().padStart(moveIndexLength)} (${
                currentPosition.level.toString().padStart(maxLevelIndexLength)
            }, ${currentPosition.line.toString().padStart(maxLevelLinesLength)}, ${
                currentPosition.column.toString().padStart(maxLevelColumnsLength)
            }) ${moveDescription}"
        )
        var exp = currentState.exp - LevelUp.levelUp(currentState.exp).exp
        println(
            "Hp: ${currentState.hp}, Atk: ${currentState.atk}, Def: ${currentState.def}, Exp: ${exp}, Exp bonus: ${currentState.expBonus}, HP bonus: ${currentState.hpBonus}"
        )
    }

    private fun apply(
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
                enemy.apply(state)
                enemy.drop.apply(state)
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