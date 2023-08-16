package net.archiloque.tacticalnexus.solver

import java.util.BitSet
import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.code.Player
import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.DatabaseMigrations
import net.archiloque.tacticalnexus.solver.database.PositionStatus
import net.archiloque.tacticalnexus.solver.database.Positions
import net.archiloque.tacticalnexus.solver.database.findNextPosition
import net.archiloque.tacticalnexus.solver.input.towers.Tower_1
import org.ktorm.database.Database
import org.ktorm.dsl.insert
import org.ktorm.support.postgresql.PostgreSqlDialect


fun main(args: Array<String>) {

    val database = Database.connect(
        "jdbc:postgresql://localhost:5432/tactical-nexus-solver",
        user = "tactical-nexus-solver",
        dialect = PostgreSqlDialect()
    )

    DatabaseMigrations.run(database)

    val tower = Tower.prepare(Tower_1().levels())
    val visitedEntities = BitSet(tower.entitiesNumber)
    visitedEntities.set(tower.startingPosition)
    val reachableEntities = BitSet(tower.entitiesNumber)
    tower.reachableEntities[tower.startingPosition].forEach {
        reachableEntities.set(it)
    }

    database.insert(Positions) {
        set(it.moves, arrayOf())
        set(it.status, PositionStatus.new)
        set(it.visitedEntities, visitedEntities)
        set(it.reachableEntities, reachableEntities)
    }

    while (true) {
        val position = findNextPosition(database)
        if (position != null) {
            Player.playPosition(position, tower)
        }
        exitProcess(0)
    }
}