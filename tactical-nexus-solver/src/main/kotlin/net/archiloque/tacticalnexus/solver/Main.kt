package net.archiloque.tacticalnexus.solver

import java.util.BitSet
import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.code.DefaultStateManager
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.Player
import net.archiloque.tacticalnexus.solver.database.Migrations
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import net.archiloque.tacticalnexus.solver.database.States.id
import net.archiloque.tacticalnexus.solver.database.findNextState
import net.archiloque.tacticalnexus.solver.entities.Tower
import net.archiloque.tacticalnexus.solver.input.towers.Tower_1
import org.ktorm.database.Database
import org.ktorm.dsl.eq
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
    val initialState = createInitialState(inputTower, playableTower)
    val stateManager = DefaultStateManager(database, playableTower, initialState)
    stateManager.save(initialState)

    while (true) {
        val state = findNextState(database)
        if (state != null) {
            Player.play(state, playableTower, stateManager)
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

fun createInitialState(inputTower: Tower, playableTower: PlayableTower): State {
    val visited = BitSet(playableTower.entitiesNumber)
    visited.set(playableTower.startingPosition)
    val reachable = BitSet(playableTower.entitiesNumber)
    playableTower.reachable[playableTower.startingPosition].forEach {
        reachable.set(it)
    }

    return State(
        -1,
        StateStatus.new,
        visited,
        reachable,

        inputTower.atk(),
        inputTower.def(),
        0,
        inputTower.hp(),
        0,
        0,

        0,
        0,
        0,
        0,
        0,

        arrayOf(),
    )
}