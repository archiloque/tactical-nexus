package net.archiloque.tacticalnexus.solver

import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.DatabaseMigrations
import net.archiloque.tacticalnexus.solver.database.PositionStatus
import net.archiloque.tacticalnexus.solver.database.Positions
import net.archiloque.tacticalnexus.solver.input.towers.Tower_1
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.select
import org.ktorm.support.postgresql.PostgreSqlDialect
import java.util.*


fun main(args: Array<String>) {

    val database = Database.connect(
        "jdbc:postgresql://localhost:5432/tactical-nexus-solver",
        user = "tactical-nexus-solver",
        dialect = PostgreSqlDialect()
    )

    DatabaseMigrations.run(database)

    val tower = Tower.prepare(Tower_1.levels)
    database.insert(Positions) {
        set(it.moves, arrayOf(10, 27))
        set(it.status, PositionStatus.new)
        val visitedEntities = BitSet(10)
        visitedEntities.set(0, true)
        visitedEntities.set(3, true)
        set(it.visitedEntities, visitedEntities)
        set(it.reachableEntities, visitedEntities)
    }

    for (row in database.from(Positions).select()) {
        println(row[Positions.id])
        println(row[Positions.moves])
        println(row[Positions.status])
        println(row[Positions.visitedEntities])
        println(row[Positions.reachableEntities])
    }
}