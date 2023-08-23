package net.archiloque.tacticalnexus.solver

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
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
    val config = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/tactical-nexus-solver"
        username = "tactical-nexus-solver"
        addDataSourceProperty("cachePrepStmts", "true")
        addDataSourceProperty("prepStmtCacheSize", "250")
        addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
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
    stateManager.save(initialState)

    if (System.getenv("SINGLE") == "true") {
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
    } else {
        val availableProcessors = Runtime.getRuntime().availableProcessors()
        for (i in 0..<(availableProcessors - 1)) {
            Thread {
                println("Staring thread ${i}")
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
                        Thread.sleep(1000)
                    }
                }
            }.start()
        }
        Thread.sleep(Long.MAX_VALUE)
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