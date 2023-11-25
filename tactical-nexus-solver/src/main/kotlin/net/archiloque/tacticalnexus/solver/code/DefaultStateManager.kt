package net.archiloque.tacticalnexus.solver.code

import java.sql.PreparedStatement
import java.sql.Types
import kotlinx.coroutines.sync.Mutex
import net.archiloque.tacticalnexus.solver.database.Mappings
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.readSqlFile
import net.archiloque.tacticalnexus.solver.entities.play.PlayableTower
import org.ktorm.database.Database

class DefaultStateManager(
    val database: Database,
    val playableTower: PlayableTower,
) :
    StateManager {

    private val deleteStateQuery: String = readSqlFile("delete_state.sql")
    private val insertStateQuery: String = readSqlFile("insert_state.sql")
    private val lockStateQuery: String = readSqlFile("lock_state.sql")
    private val unlockStateQuery: String = readSqlFile("unlock_state.sql")

    private val maxScoreLock = Mutex()
    private var maxScore = 0
    private var maxScoreMoves: ShortArray = shortArrayOf()

    override fun save(state: State) {
        val stateScore = score(state)
        if (stateScore > maxScore) {
            synchronized(maxScoreLock) {
                if (stateScore > maxScore) {
                    maxScore = stateScore
                    maxScoreMoves = state.moves
                    println("Found best score at $maxScore: ${maxScoreMoves.joinToString(",")}")
                }
            }
        }
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

    private fun score(state: State): Int {
        return if (state.visited[playableTower.starScorePosition]) {
            state.hp + ((state.atk + state.def) * state.level)
        } else if (state.visited[playableTower.checkScorePosition]) {
            state.hp + (5 * ((state.atk + state.def) * state.level))
        } else {
            0
        }
    }

    private fun deleteLowerStates(state: State, stateId: Int) {
        database.useConnection { connection ->
            connection.prepareStatement(deleteStateQuery).use { statement ->
                Mappings.BitSetSqlType.setParameter(statement, 1, state.reachable)
                insertStateCommonParams(statement, state, 2)

                statement.setObject(14, StateStatus.in_progress.name, Types.OTHER)
                statement.setInt(15, stateId)
            }
        }
    }

    private fun tryInsertState(state: State): Int? {
        database.useConnection { connection ->
            connection.prepareStatement(insertStateQuery).use { statement ->
                statement.setObject(1, StateStatus.new.name, Types.OTHER)

                Mappings.BitSetSqlType.setParameter(statement, 2, state.reachable)

                insertStateCommonParams(statement, state, 3)

                Mappings.BitSetSqlType.setParameter(statement, 15, state.visited)
                Mappings.ShortArraySqlType.setParameter(statement, 16, state.moves)
                statement.setShort(17, state.level)

                Mappings.BitSetSqlType.setParameter(statement, 18, state.reachable)
                insertStateCommonParams(statement, state, 19)
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

    private fun insertStateCommonParams(
        statement: PreparedStatement,
        state: State,
        firstParameterIndex: Int,
    ) {
        statement.setObject(firstParameterIndex, state.atk)
        statement.setInt(firstParameterIndex + 1, state.def)
        statement.setInt(firstParameterIndex + 2, state.exp)
        statement.setInt(firstParameterIndex + 3, state.hp)

        statement.setShort(firstParameterIndex + 4, state.expBonus)
        statement.setShort(firstParameterIndex + 5, state.hpBonus)

        statement.setShort(firstParameterIndex + 6, state.blueKeys)
        statement.setShort(firstParameterIndex + 7, state.crimsonKeys)
        statement.setShort(firstParameterIndex + 8, state.greenblueKeys)
        statement.setShort(firstParameterIndex + 9, state.platinumKeys)
        statement.setShort(firstParameterIndex + 10, state.violetKeys)
        statement.setShort(firstParameterIndex + 11, state.yellowKeys)
    }
}