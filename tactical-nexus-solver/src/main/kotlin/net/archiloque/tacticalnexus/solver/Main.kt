package net.archiloque.tacticalnexus.solver

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.util.BitSet
import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.code.DefaultStateManager
import net.archiloque.tacticalnexus.solver.code.Player
import net.archiloque.tacticalnexus.solver.database.Migrations
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import net.archiloque.tacticalnexus.solver.database.States.id
import net.archiloque.tacticalnexus.solver.database.findNextStates
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.play.PlayableTower
import net.archiloque.tacticalnexus.solver.input.towers.Tower_1
import org.ktorm.database.Database
import org.ktorm.dsl.inList
import org.ktorm.dsl.update
import org.ktorm.support.postgresql.PostgreSqlDialect


fun main() {
    val config = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/tactical-nexus-solver"
        username = "tactical-nexus-solver"
        addDataSourceProperty("cachePrepStmts", "true")
        addDataSourceProperty("prepStmtCacheSize", "250")
        addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        maximumPoolSize = Runtime.getRuntime().availableProcessors()
    }

    val database = Database.connect(
        HikariDataSource(config),
        dialect = PostgreSqlDialect(),
        //logger = ConsoleLogger(LogLevel.DEBUG)
    )

    Migrations.run(database)

    val inputTower = Tower_1()
    val playableTower = PlayableTower.prepare(inputTower)
    val initialState = createInitialState(inputTower, playableTower)
    val stateManager = DefaultStateManager(database, inputTower, playableTower, initialState)
    stateManager.save(listOf(initialState))

    if (System.getenv("SINGLE") == "true") {
        while (true) {
            val states = findNextStates(database)
            if (states.isNotEmpty()) {
                processStates(states, playableTower, stateManager, database)
            } else {
                exitProcess(0)
            }
        }
    } else {
        val availableProcessors = Runtime.getRuntime().availableProcessors()
        for (i in 0..<(availableProcessors - 1)) {
            Thread {
                println("Staring thread ${i}")
                while (true) {
                    val states = findNextStates(database)
                    if (states.isNotEmpty()) {
                        processStates(states, playableTower, stateManager, database)
                    } else {
                        Thread.sleep(1000)
                    }
                }
            }.start()
        }
        Thread.sleep(Long.MAX_VALUE)
    }
}

private fun processStates(
    states: List<State>,
    playableTower: PlayableTower,
    stateManager: DefaultStateManager,
    database: Database,
) {
    val newStates = mutableListOf<State>()
    for (state in states) {
        Player.play(state, playableTower, stateManager, newStates)
    }
    database.useTransaction {
        database.update(States) {
            set(it.status, StateStatus.processed)
            where {
                id inList states.map { it.id }
            }
        }
    }
    stateManager.save(newStates)
}


fun createInitialState(inputTower: Tower, playableTower: PlayableTower): State {
    val visited = BitSet(playableTower.entitiesNumber)
    val reachable = BitSet(playableTower.entitiesNumber)
    playableTower.reachableByStartingPosition.forEach {
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