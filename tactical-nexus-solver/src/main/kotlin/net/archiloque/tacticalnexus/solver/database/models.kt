package net.archiloque.tacticalnexus.solver.database

import org.ktorm.schema.Table
import org.ktorm.schema.enum
import org.ktorm.schema.int

public enum class PositionStatus(val value: String) {
    new("new"),
    in_progress("in_progress"),
    processed("processed"),
}

public object Positions : Table<Nothing>("positions") {
    val id = int("id").primaryKey()
    val visitedEntities = registerColumn("visited_entities", Mappings.BitSetSqlType)
    val reachableEntities = registerColumn("reachable_entities", Mappings.BitSetSqlType)
    val moves = registerColumn("moves", Mappings.IntArraySqlType)
    val status = enum<PositionStatus>("status")
}
