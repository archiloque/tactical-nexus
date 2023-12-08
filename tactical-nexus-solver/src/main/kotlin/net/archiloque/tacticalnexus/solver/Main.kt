package net.archiloque.tacticalnexus.solver

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Types
import java.util.BitSet
import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.code.DefaultStateManager
import net.archiloque.tacticalnexus.solver.code.PathPrinter
import net.archiloque.tacticalnexus.solver.code.Player
import net.archiloque.tacticalnexus.solver.database.Mappings
import net.archiloque.tacticalnexus.solver.database.Migrations
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.findNextStates
import net.archiloque.tacticalnexus.solver.database.readSqlFile
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.play.PlayableTower
import net.archiloque.tacticalnexus.solver.entities.play.TowerPreparer
import net.archiloque.tacticalnexus.solver.input.towers.Tower_1
import org.ktorm.database.Database
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

    val inputTower = Tower_1()
    val playableTower = TowerPreparer(inputTower).prepare()
    playableTower.printAll()
    val initialState = createInitialState(inputTower, playableTower)
    if (System.getenv("MOVES") != null) {
        val moves = System.getenv("MOVES").split(",").map { Integer.parseInt(it.trim()) }.toIntArray()
        PathPrinter(inputTower, playableTower, initialState).printMoves(moves)
        exitProcess(0)
    }

    val database = Database.connect(
        HikariDataSource(config),
        dialect = PostgreSqlDialect(),
        // logger = ConsoleLogger(LogLevel.DEBUG)
    )

    Migrations.run(database)

    val stateManager = DefaultStateManager(database, playableTower)
    stateManager.save(initialState)

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
                println("Staring thread $i")
                while (true) {
                    val states = findNextStates(database)
                    if (states.isNotEmpty()) {
                        processStates(states, playableTower, stateManager, database)
                    } else {
                        println("Thread ${i}, nothing to do, sleeping")
                        Thread.sleep(1000)
                    }
                }
            }.start()
        }
        Thread.sleep(Long.MAX_VALUE)
    }
}

private val updateStateQuery: String = readSqlFile("update_state.sql")

private fun processStates(
    states: List<State>,
    playableTower: PlayableTower,
    stateManager: DefaultStateManager,
    database: Database,
) {
    for (state in states) {
        Player.play(state, playableTower, stateManager)
    }
    database.useConnection { connection ->
        connection.prepareStatement(updateStateQuery).use { statement ->
            statement.setObject(1, StateStatus.processed.name, Types.OTHER)
            Mappings.IntArraySqlType.setParameter(statement, 2, states.map { it.id }.toIntArray())
        }
    }
}

fun createInitialState(inputTower: Tower, playableTower: PlayableTower): State {
    val visited = BitSet(playableTower.entitiesCount)
    val reachable = BitSet(playableTower.entitiesCount)
    playableTower.reachableByStartingPosition.forEach {
        reachable.set(it)
    }

    return State(
        -1,
        StateStatus.new,
        visited,

        inputTower.atk(),
        inputTower.def(),
        0,
        inputTower.hp(),
        100,
        100,

        0,
        0,
        0,
        0,
        0,
        0,

        reachable,
        shortArrayOf(),
        shortArrayOf(),
        0,
    )
}