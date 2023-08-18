package net.archiloque.tacticalnexus.solver.database

import java.sql.Types
import java.util.BitSet
import org.ktorm.database.Database
import org.ktorm.dsl.QueryRowSet
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.BaseTable
import org.ktorm.schema.enum
import org.ktorm.schema.int

enum class StateStatus(val value: String) {
    new("new"),
    in_progress("in_progress"),
    processed("processed"),
}

data class State(
    var id: Int,

    var status: StateStatus,

    var visited: BitSet,
    var reachable: BitSet,

    var atk: Int,
    var def: Int,
    var hp: Int,

    var blue_keys: Int,
    var crimson_keys: Int,
    var platinum_keys: Int,
    var violet_keys: Int,
    var yellow_keys: Int,

    var moves: Array<Int>,
)

object States : BaseTable<State>("states") {
    val id = int("id").primaryKey()

    val status = enum<StateStatus>("status")

    val visited = bitSet("visited")

    val reachable = bitSet("reachable")

    val moves = intArray("moves")

    val atk = int("atk")
    val def = int("def")
    val hp = int("hp")

    val blue_keys = int("blue_keys")
    val crimson_keys = int("crimson_keys")
    val platinum_keys = int("platinum_keys")
    val violet_keys = int("violet_keys")
    val yellow_keys = int("yellow_keys")

    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean): State {
        return State(
            id = row[id]!!,

            status = row[status]!!,

            visited = row[visited]!!,
            reachable = row[reachable]!!,

            moves = row[moves]!!,

            atk = row[atk]!!,
            def = row[def]!!,
            hp = row[hp]!!,

            blue_keys = row[blue_keys]!!,
            crimson_keys = row[crimson_keys]!!,
            platinum_keys = row[platinum_keys]!!,
            violet_keys = row[violet_keys]!!,
            yellow_keys = row[yellow_keys]!!,
        )
    }
}

fun readSqlFile(path: String): String {
    return Database.javaClass.getResource("/sql/${path}").readText()
}

val Database.positions get() = this.sequenceOf(States)

val findNextStateQuery: String = readSqlFile("find_next_state.sql")

fun findNextState(database: Database): State? {
    database.useConnection { conn ->
            conn.prepareStatement(findNextStateQuery).use { statement ->
                statement.setObject(1, StateStatus.in_progress.name, Types.OTHER)
                statement.setObject(2, StateStatus.new.name, Types.OTHER)
                val result = statement.executeQuery()
                if (result.next()) {
                    return State(
                        result.getInt(1),
                        StateStatus.in_progress,
                        Mappings.BitSetSqlType.getResult(result, 2)!!,
                        Mappings.BitSetSqlType.getResult(result, 3)!!,

                        result.getInt(4),
                        result.getInt(5),
                        result.getInt(6),

                        result.getInt(7),
                        result.getInt(8),
                        result.getInt(9),
                        result.getInt(10),
                        result.getInt(11),

                        Mappings.IntArraySqlType.getResult(result, 12)!!,
                    )
                } else {
                    return null
                }
            }
    }
}