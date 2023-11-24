package net.archiloque.tacticalnexus.solver.code

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

                statement.setInt(2, state.atk)
                statement.setInt(3, state.def)
                statement.setInt(4, state.exp)
                statement.setInt(5, state.hp)

                statement.setShort(6, state.expBonus)
                statement.setShort(7, state.hpBonus)

                statement.setShort(8, state.blueKeys)
                statement.setShort(9, state.crimsonKeys)
                statement.setShort(10, state.platinumKeys)
                statement.setShort(11, state.violetKeys)
                statement.setShort(12, state.yellowKeys)

                statement.setObject(1, StateStatus.in_progress.name, Types.OTHER)
                statement.setInt(14, stateId)
            }
        }
    }

    private fun tryInsertState(state: State): Int? {
        database.useConnection { connection ->
            connection.prepareStatement(insertStateQuery).use { statement ->
                statement.setObject(1, StateStatus.new.name, Types.OTHER)

                Mappings.BitSetSqlType.setParameter(statement, 2, state.reachable)

                statement.setObject(3, state.atk)
                statement.setInt(4, state.def)
                statement.setInt(5, state.exp)
                statement.setInt(6, state.hp)

                statement.setShort(7, state.expBonus)
                statement.setShort(8, state.hpBonus)

                statement.setShort(9, state.blueKeys)
                statement.setShort(10, state.crimsonKeys)
                statement.setShort(11, state.platinumKeys)
                statement.setShort(12, state.violetKeys)
                statement.setShort(13, state.yellowKeys)

                Mappings.BitSetSqlType.setParameter(statement, 14, state.visited)
                Mappings.ShortArraySqlType.setParameter(statement, 15, state.moves)
                statement.setShort(16, state.level)

                Mappings.BitSetSqlType.setParameter(statement, 17, state.reachable)
                statement.setInt(18, state.atk)
                statement.setInt(19, state.def)
                statement.setInt(20, state.exp)
                statement.setInt(21, state.hp)

                statement.setShort(22, state.expBonus)
                statement.setShort(23, state.hpBonus)

                statement.setShort(24, state.blueKeys)
                statement.setShort(25, state.crimsonKeys)
                statement.setShort(26, state.platinumKeys)
                statement.setShort(27, state.violetKeys)
                statement.setShort(28, state.yellowKeys)
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
}