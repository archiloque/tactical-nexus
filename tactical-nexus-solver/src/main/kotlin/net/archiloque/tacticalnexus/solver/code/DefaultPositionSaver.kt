package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.Position
import net.archiloque.tacticalnexus.solver.database.PositionStatus
import net.archiloque.tacticalnexus.solver.database.Positions
import org.ktorm.database.Database
import org.ktorm.support.postgresql.bulkInsertOrUpdate

class DefaultPositionSaver(val database: Database) : PositionSaver {

    override fun save(position: Position) {
        database.useTransaction {
            database.bulkInsertOrUpdate(Positions) {
                item {
                    set(it.status, PositionStatus.new)

                    set(it.visitedEntities, position.visitedEntities)
                    set(it.reachableEntities, position.reachableEntities)

                    set(it.atk, position.atk)
                    set(it.def, position.def)
                    set(it.hp, position.hp)

                    set(it.blue_keys, position.blue_keys)
                    set(it.crimson_keys, position.crimson_keys)
                    set(it.platinum_keys, position.platinum_keys)
                    set(it.violet_keys, position.violet_keys)
                    set(it.yellow_keys, position.yellow_keys)

                    set(it.moves, position.moves)
                }
                onConflict(
                    Positions.visitedEntities,
                    Positions.reachableEntities,

                    Positions.atk,
                    Positions.def,
                    Positions.hp,

                    Positions.blue_keys,
                    Positions.crimson_keys,
                    Positions.platinum_keys,
                    Positions.violet_keys,
                    Positions.yellow_keys,
                ) {
                    doNothing()
                }
            }
        }
    }
}