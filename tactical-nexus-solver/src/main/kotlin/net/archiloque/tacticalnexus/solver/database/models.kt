package net.archiloque.tacticalnexus.solver.database

import java.sql.Types
import org.ktorm.database.Database

fun readSqlFile(path: String): String {
    return Database::class.java.getResource("/sql/${path}")!!.readText()
}

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
                    result.getInt(12),
                    result.getInt(13),
                    result.getInt(14),

                    Mappings.IntArraySqlType.getResult(result, 15)!!,
                )
            } else {
                return null
            }
        }
    }
}