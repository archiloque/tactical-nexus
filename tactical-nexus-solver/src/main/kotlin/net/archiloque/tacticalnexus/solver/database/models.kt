package net.archiloque.tacticalnexus.solver.database

import java.sql.Types
import org.ktorm.database.Database
import org.ktorm.database.asIterable

fun readSqlFile(path: String): String {
    return Database::class.java.getResource("/sql/${path}")!!.readText()
}

val findNextStatesQuery: String = readSqlFile("find_next_states.sql")

fun findNextStates(database: Database): List<State> {
    database.useConnection { conn ->
        conn.prepareStatement(findNextStatesQuery).use { statement ->
            statement.setObject(1, StateStatus.in_progress.name, Types.OTHER)
            statement.setObject(2, StateStatus.new.name, Types.OTHER)
            return statement.executeQuery().asIterable().map {
                State(
                    it.getInt(1),
                    StateStatus.in_progress,
                    Mappings.BitSetSqlType.getResult(it, 2)!!,

                    it.getInt(3),
                    it.getInt(4),
                    it.getInt(5),
                    it.getInt(6),

                    it.getInt(7),
                    it.getInt(8),

                    it.getInt(9),
                    it.getInt(10),
                    it.getInt(11),
                    it.getInt(12),
                    it.getInt(13),

                    Mappings.BitSetSqlType.getResult(it, 14)!!,
                    Mappings.IntArraySqlType.getResult(it, 15)!!,
                    it.getInt(16)
                )
            }
        }
    }
}