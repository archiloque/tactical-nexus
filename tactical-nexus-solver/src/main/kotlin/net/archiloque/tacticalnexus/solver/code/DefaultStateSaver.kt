package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import org.ktorm.database.Database
import org.ktorm.support.postgresql.bulkInsertOrUpdate

class DefaultStateSaver(val database: Database) : StateSaver {

    override fun save(state: State) {
        database.useTransaction {
            database.bulkInsertOrUpdate(States) {
                item {
                    set(it.status, StateStatus.new)

                    set(it.visitedEntities, state.visitedEntities)
                    set(it.reachableEntities, state.reachableEntities)

                    set(it.atk, state.atk)
                    set(it.def, state.def)
                    set(it.hp, state.hp)

                    set(it.blue_keys, state.blue_keys)
                    set(it.crimson_keys, state.crimson_keys)
                    set(it.platinum_keys, state.platinum_keys)
                    set(it.violet_keys, state.violet_keys)
                    set(it.yellow_keys, state.yellow_keys)

                    set(it.moves, state.moves)
                }
                onConflict(
                    States.visitedEntities,
                    States.reachableEntities,

                    States.atk,
                    States.def,
                    States.hp,

                    States.blue_keys,
                    States.crimson_keys,
                    States.platinum_keys,
                    States.violet_keys,
                    States.yellow_keys,
                ) {
                    doNothing()
                }
            }
        }
    }
}