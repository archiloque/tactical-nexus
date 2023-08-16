package net.archiloque.tacticalnexus.solver.database

import java.sql.Types
import java.util.BitSet
import org.ktorm.database.Database
import org.ktorm.dsl.QueryRowSet
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.BaseTable
import org.ktorm.schema.enum
import org.ktorm.schema.int

enum class PositionStatus(val value: String) {
    new("new"),
    in_progress("in_progress"),
    processed("processed"),
}

data class Position(
    val id: Int,
    val visitedEntities: BitSet,
    val reachableEntities: BitSet,
    val moves: Array<Int>,
    val status: PositionStatus,
)

object Positions : BaseTable<Position>("positions") {
    val id = int("id").primaryKey()
    val visitedEntities = bitSet("visited_entities")
    val reachableEntities = bitSet("reachable_entities")
    val moves = intArray("moves")
    val status = enum<PositionStatus>("status")
    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean): Position {
        return Position(
            id = row[id] ?: 0,
            visitedEntities = row[visitedEntities] ?: BitSet(),
            reachableEntities = row[reachableEntities] ?: BitSet(),
            moves = row[moves] ?: arrayOf(),
            status = row[status] ?: PositionStatus.new,
        )
    }
}

public fun readSqlFile(path: String): String {
    return Database.javaClass.getResource("/sql/${path}").readText()
}

val Database.positions get() = this.sequenceOf(Positions)

val findNextPositionQuery: String = readSqlFile("find_next_position.sql")
public fun findNextPosition(database: Database): Position? {
    database.useConnection { conn ->
        database.useTransaction {
            conn.prepareStatement(findNextPositionQuery).use { statement ->
                statement.setObject(1, PositionStatus.in_progress.name, Types.OTHER)
                statement.setObject(2, PositionStatus.new.name, Types.OTHER)
                val result = statement.executeQuery()
                if (result.next()) {
                    return Position(
                        result.getInt(1),
                        Mappings.BitSetSqlType.getResult(result, 2)!!,
                        Mappings.BitSetSqlType.getResult(result, 3)!!,
                        Mappings.IntArraySqlType.getResult(result, 4)!!,
                        PositionStatus.in_progress
                    )
                } else {
                    return null
                }
            }
        }
    }
}