package net.archiloque.tacticalnexus.solver.database

import org.ktorm.dsl.QueryRowSet
import org.ktorm.schema.BaseTable
import org.ktorm.schema.enum
import org.ktorm.schema.int

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