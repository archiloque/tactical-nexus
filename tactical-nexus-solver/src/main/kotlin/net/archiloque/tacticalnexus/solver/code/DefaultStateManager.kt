package net.archiloque.tacticalnexus.solver.code

import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import net.archiloque.tacticalnexus.solver.entities.input.InputEntityType
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.play.Door
import net.archiloque.tacticalnexus.solver.entities.play.Enemy
import net.archiloque.tacticalnexus.solver.entities.play.Item
import net.archiloque.tacticalnexus.solver.entities.play.Key
import net.archiloque.tacticalnexus.solver.entities.play.LevelUp
import net.archiloque.tacticalnexus.solver.entities.play.LevelUpType
import net.archiloque.tacticalnexus.solver.entities.play.PlayEntity
import net.archiloque.tacticalnexus.solver.entities.play.PlayEntityType
import net.archiloque.tacticalnexus.solver.entities.play.PlayableTower
import net.archiloque.tacticalnexus.solver.entities.play.Position
import org.ktorm.database.Database
import org.ktorm.support.postgresql.bulkInsertOrUpdate

class DefaultStateManager(
    val database: Database,
    private val tower: Tower,
    val playableTower: PlayableTower,
    private val initialState: State,
) :
    StateManager {

    override fun save(states: List<State>) {
        if (states.isNotEmpty()) {
            synchronized(this) {
                database.useTransaction {
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
        }
    }

    override fun reachedExit(state: State) {
        synchronized(this) {
            println(state)
            val currentState = initialState.copy()

            val moveIndexLength = state.moves.size.toString().length
            val positionedEntities = state.moves.filter { it >= 0 }.map { playableTower.playEntities[it] }
            val maxLevelIndexLength =
                positionedEntities.map { it.getPositions().map { it.level }.max() }.max().toString().length
            val maxLevelColumnsLength =
                positionedEntities.map { it.getPositions().map { it.column }.max() }.max().toString().length
            val maxLevelLinesLength =
                positionedEntities.map { it.getPositions().map { it.line }.max() }.max().toString().length
            var index = 1
            val initialPosition = playableTower.startingPositionPosition
            var currentLevelUpIndex = 0
            printStatus(
                0,
                moveIndexLength,
                maxLevelIndexLength,
                maxLevelLinesLength,
                maxLevelColumnsLength,
                initialPosition,
                "Starting"
            )
            printMove(arrayOf(initialPosition), initialPosition, tower)
            println()
            var lastPosition: Position = initialPosition
            for (move in state.moves) {
                if (move >= 0) {
                    val positionedEntity = playableTower.playEntities[move]
                    apply(positionedEntity, currentState)
                    printStatus(
                        index,
                        moveIndexLength,
                        maxLevelIndexLength,
                        maxLevelLinesLength,
                        maxLevelColumnsLength,
                        currentState,
                        positionedEntity
                    )
                    printMove(positionedEntity.getPositions(), lastPosition, tower)
                    println()

                    lastPosition = positionedEntity.getPositions().last()
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
                        maxLevelIndexLength,
                        maxLevelLinesLength,
                        maxLevelColumnsLength,
                        lastPosition,
                        description
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
        maxLevelIndexLength: Int,
        maxLevelLinesLength: Int,
        maxLevelColumnsLength: Int,
        currentState: State,
        entity: PlayEntity,
    ) {
        entity.description().forEach { positionedDescription ->
            printStatus(
                index,
                moveIndexLength,
                maxLevelIndexLength,
                maxLevelLinesLength,
                maxLevelColumnsLength,
                positionedDescription.position,
                positionedDescription.description
            )
        }
        val exp = currentState.exp - LevelUp.levelUp(currentState.exp).exp
        println(
            "Hp: ${currentState.hp}, Atk: ${currentState.atk}, Def: ${currentState.def}, Exp: ${exp}, Exp bonus: ${currentState.expBonus}, HP bonus: ${currentState.hpBonus}"
        )
    }

    private fun printStatus(
        index: Int,
        moveIndexLength: Int,
        maxLevelIndexLength: Int,
        maxLevelLinesLength: Int,
        maxLevelColumnsLength: Int,
        position: Position,
        description: String,
    ) {
        println(
            "${index.toString().padStart(moveIndexLength)} (${
                position.level.toString().padStart(maxLevelIndexLength)
            }, ${position.line.toString().padStart(maxLevelLinesLength)}, ${
                position.column.toString().padStart(maxLevelColumnsLength)
            }) ${description}"
        )
    }

    private fun apply(
        inputEntity: PlayEntity,
        state: State,
    ) {
        when (inputEntity.getType()) {
            PlayEntityType.Door -> {
                val door = inputEntity as Door
                door.apply(state)
            }

            PlayEntityType.Enemy -> {
                val enemy = inputEntity as Enemy
                enemy.apply(state)
                enemy.drop.apply(state)
            }

            PlayEntityType.Exit -> {
            }

            PlayEntityType.ItemGroup -> {
                val item = inputEntity as Item
                item.apply(state)
            }

            PlayEntityType.Key -> {
                val key = inputEntity as Key
                key.apply(state)
            }

            PlayEntityType.UpStaircase -> {
            }
        }
    }

    private fun printMove(currentPositions: Array<Position>, previousPosition: Position, tower: Tower) {
        val levelIndex = currentPositions.first().level
        val level = tower.levels()[levelIndex]
        for ((lineIndex, line) in level.entities.withIndex()) {
            println(line.mapIndexed { columnIndex, entity ->
                if (currentPositions.contains(Position(levelIndex, lineIndex, columnIndex))) {
                    'X'
                } else if ((levelIndex == previousPosition.level) && (lineIndex == previousPosition.line) && (columnIndex == previousPosition.column)) {
                    '□'
                } else if ((entity != null) && (entity.getType() == InputEntityType.Wall)) {
                    '#'
                } else {
                    ' '
                }
            }.joinToString(""))
        }
    }
}