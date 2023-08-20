package net.archiloque.tacticalnexus.solver

import java.util.BitSet
import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.code.DefaultStateSaver
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.Player
import net.archiloque.tacticalnexus.solver.database.Migrations
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import net.archiloque.tacticalnexus.solver.database.States.id
import net.archiloque.tacticalnexus.solver.database.findNextState
import net.archiloque.tacticalnexus.solver.input.towers.Tower_1
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.support.postgresql.PostgreSqlDialect


fun main(args: Array<String>) {

    val database = Database.connect(
        "jdbc:postgresql://localhost:5432/tactical-nexus-solver",
        user = "tactical-nexus-solver",
        dialect = PostgreSqlDialect(),
        //logger = ConsoleLogger(LogLevel.DEBUG)
    )

    Migrations.run(database)

    val inputTower = Tower_1()
    val playableTower = PlayableTower.prepare(inputTower)
    val visited = BitSet(playableTower.entitiesNumber)
    visited.set(playableTower.startingPosition)
    val reachable = BitSet(playableTower.entitiesNumber)
    playableTower.reachable[playableTower.startingPosition].forEach {
        reachable.set(it)
    }

    database.insert(States) {
        set(it.moves, arrayOf())

        set(it.status, StateStatus.new)

        set(it.visited, visited)
        set(it.reachable, reachable)

        set(it.atk, inputTower.atk())
        set(it.def, inputTower.def())
        set(it.hp, inputTower.hp())
        set(it.blue_keys, 0)
        set(it.crimson_keys, 0)
        set(it.platinum_keys, 0)
        set(it.violet_keys, 0)
        set(it.yellow_keys, 0)
    }

    val stateSaver = DefaultStateSaver(database)

    while (true) {
        val state = findNextState(database)
        if (state != null) {
            Player.play(state, playableTower, stateSaver)
            database.useTransaction {
                database.update(States) {
                    set(it.status, StateStatus.processed)
                    where {
                        id eq state.id
                    }
                }
            }
        } else {
            exitProcess(0)
        }
    }
}