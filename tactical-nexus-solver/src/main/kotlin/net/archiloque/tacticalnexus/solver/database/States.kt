package net.archiloque.tacticalnexus.solver.database

import org.ktorm.dsl.QueryRowSet
import org.ktorm.schema.BaseTable
import org.ktorm.schema.enum
import org.ktorm.schema.int
import org.ktorm.schema.short

object States : BaseTable<State>("states") {
    val id = int("id").primaryKey()

    val visited = bitSet("visited")

    val atk = int("atk")
    val def = int("def")
    val exp = int("exp")
    val hp = int("hp")

    val expBonus = short("exp_bonus")
    val hpBonus = short("hp_bonus")

    val blue_keys = short("blue_keys")
    val green_blue_keys = short("green_blue_keys")
    val crimson_keys = short("crimson_keys")
    val platinum_keys = short("platinum_keys")
    val violet_keys = short("violet_keys")
    val yellow_keys = short("yellow_keys")

    val status = enum<StateStatus>("status")

    val reachable = bitSet("reachable")
    val moves = shortArray("moves")
    val oneWays = shortArray("one_ways")
    val level = short("level")

    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean): State {
        return State(
            id = row[id]!!,

            visited = row[visited]!!,

            atk = row[atk]!!,
            def = row[def]!!,
            exp = row[exp]!!,
            hp = row[hp]!!,

            expBonus = row[expBonus]!!,
            hpBonus = row[hpBonus]!!,

            blueKeys = row[blue_keys]!!,
            crimsonKeys = row[crimson_keys]!!,
            greenBlueKeys = row[green_blue_keys]!!,
            platinumKeys = row[platinum_keys]!!,
            violetKeys = row[violet_keys]!!,
            yellowKeys = row[yellow_keys]!!,

            status = row[status]!!,

            reachable = row[reachable]!!,
            moves = row[moves]!!,
            oneWays = row[oneWays]!!,
            level = row[level]!!,
        )
    }
}