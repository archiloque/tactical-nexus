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
    val exp = int("exp")
    val level = int("level")
    val hp = int("hp")

    val expBonus = int("exp_bonus")
    val hpBonus = int("hp_bonus")

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
            exp = row[exp]!!,
            level = row[level]!!,
            hp = row[hp]!!,

            expBonus = row[expBonus]!!,
            hpBonus = row[hpBonus]!!,

            blueKeys = row[blue_keys]!!,
            crimsonKeys = row[crimson_keys]!!,
            platinumKeys = row[platinum_keys]!!,
            violetKeys = row[violet_keys]!!,
            yellowKeys = row[yellow_keys]!!,
        )
    }
}