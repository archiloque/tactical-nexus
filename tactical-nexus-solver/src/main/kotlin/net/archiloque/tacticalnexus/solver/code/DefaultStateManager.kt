package net.archiloque.tacticalnexus.solver.code

import java.sql.Types
import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.database.Mappings
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import net.archiloque.tacticalnexus.solver.database.readSqlFile
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
import org.ktorm.dsl.and
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.lessEq
import org.ktorm.dsl.neq

class DefaultStateManager(
    val database: Database,
    private val tower: Tower,
    val playableTower: PlayableTower,
    private val initialState: State,
) :
    StateManager {

    private val insertStateQuery: String = readSqlFile("insert_state.sql")
    private val lockStateQuery: String = readSqlFile("lock_state.sql")
    private val unlockStateQuery: String = readSqlFile("unlock_state.sql")

    override fun save(state: State) {
        val stateLockId = state.reachable.hashCode()
        lockState(lockStateQuery, stateLockId)
        val stateId = tryInsertState(state)
        if (stateId != null) {
            deleteLowerStates(state, stateId)
        }
        lockState(unlockStateQuery, stateLockId)
    }

    private fun lockState(query: String, stateLockId: Int) {
        database.useConnection { connection ->
            connection.prepareStatement(query).use { statement ->
                statement.setInt(1, stateLockId)
            }
        }
    }

    private fun deleteLowerStates(state: State, stateId: Int) {
        database.delete(States) {
            (it.reachable eq state.reachable) and

                    (it.atk lessEq state.atk) and
                    (it.def lessEq state.def) and
                    (it.exp lessEq state.exp) and
                    (it.hp lessEq state.hp) and

                    (it.expBonus lessEq state.expBonus) and
                    (it.hpBonus lessEq state.hpBonus) and

                    (it.blue_keys lessEq state.blueKeys) and
                    (it.crimson_keys lessEq state.crimsonKeys) and
                    (it.platinum_keys lessEq state.platinumKeys) and
                    (it.violet_keys lessEq state.violetKeys) and
                    (it.yellow_keys lessEq state.yellowKeys) and
                    (it.status neq  StateStatus.in_progress) and
                    (it.id neq stateId)
        }
    }

    private fun tryInsertState(state: State): Int? {
        database.useConnection { connection ->
            connection.prepareStatement(insertStateQuery).use { statement ->
                statement.setObject(1, StateStatus.new.name, Types.OTHER)
                Mappings.BitSetSqlType.setParameter(statement, 2, state.visited)
                Mappings.BitSetSqlType.setParameter(statement, 3, state.reachable)
                statement.setObject(4, state.atk)
                statement.setInt(5, state.def)
                statement.setInt(6, state.exp)
                statement.setInt(7, state.hp)

                statement.setInt(8, state.expBonus)
                statement.setInt(9, state.hpBonus)

                statement.setInt(10, state.blueKeys)
                statement.setInt(11, state.crimsonKeys)
                statement.setInt(12, state.platinumKeys)
                statement.setInt(13, state.violetKeys)
                statement.setInt(14, state.yellowKeys)

                Mappings.IntArraySqlType.setParameter(statement, 15, state.moves)

                Mappings.BitSetSqlType.setParameter(statement, 16, state.reachable)

                statement.setObject(17, state.atk)
                statement.setInt(18, state.def)
                statement.setInt(19, state.exp)
                statement.setInt(20, state.hp)

                statement.setInt(21, state.expBonus)
                statement.setInt(22, state.hpBonus)

                statement.setInt(23, state.blueKeys)
                statement.setInt(24, state.crimsonKeys)
                statement.setInt(25, state.platinumKeys)
                statement.setInt(26, state.violetKeys)
                statement.setInt(27, state.yellowKeys)
                val resultSet = statement.executeQuery()
                if (resultSet.next()) {
                    // The state has been inserted
                    return resultSet.getInt(1)
                } else {
                    return null
                }
            }
        }
    }

    override fun reachedExit(moves: Array<Int>) {
        synchronized(this) {
            val currentState = initialState.copy()

            val moveIndexLength = moves.size.toString().length
            val positionedEntities = moves.filter { it >= 0 }.map { playableTower.playEntities[it] }
            positionedEntities.map { it.getPositions().map { it.level }.max() }.max().toString().length
            var index = 1
            val initialPosition = playableTower.startingPositionPosition
            var currentLevelUpIndex = 0
            printStatus(
                0,
                moveIndexLength,
                initialPosition,
                "Starting"
            )
            printMove(arrayOf(initialPosition), initialPosition, tower)
            println()
            var lastPosition: Position = initialPosition
            for (move in moves) {
                if (move >= 0) {
                    val positionedEntity = playableTower.playEntities[move]
                    apply(positionedEntity, currentState)
                    if (positionedEntity.getType() != PlayEntityType.UpStaircase) {
                        printStatus(
                            index,
                            moveIndexLength,
                            currentState,
                            positionedEntity
                        )
                        printMove(positionedEntity.getPositions(), lastPosition, tower)
                    }
                    println()

                    lastPosition = positionedEntity.getPositions().last()
                } else {
                    currentLevelUpIndex++
                    val levelUp = LevelUp.levelUp(currentState.exp)
                    val levelUpType = LevelUpType.entries.find { it.type == move }!!
                    val description = when (levelUpType) {
                        LevelUpType.atk -> {
                            "Gain ${levelUp.atk} atk"
                        }

                        LevelUpType.def -> {
                            "Gain ${levelUp.def} def"
                        }

                        LevelUpType.blueKeys -> {
                            "Gain ${LevelUp.BLUE_KEYS_NUMBER} blue key(s)"
                        }

                        LevelUpType.crimsonKeys -> {
                            "Gain ${LevelUp.CRIMSON_KEYS_NUMBER} crimson key(s)"
                        }

                        LevelUpType.yellowKeys -> {
                            "Gain ${LevelUp.YELLOW_KEYS_NUMBER} yellow key(s)"
                        }
                    }
                    Enemy.applyLevelUp(levelUpType, currentState, levelUp)
                    printStatus(
                        index,
                        moveIndexLength,
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
        currentState: State,
        entity: PlayEntity,
    ) {
        entity.description().forEach { positionedDescription ->
            printStatus(
                index,
                moveIndexLength,
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
        position: Position,
        description: String,
    ) {
        println(
            "${index.toString().padStart(moveIndexLength)} (${
                position.level.toString().padStart(3)
            }, ${position.line.toString().padStart(2)}, ${
                position.column.toString().padStart(2)
            }) $description"
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