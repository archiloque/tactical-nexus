package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.database.States
import org.ktorm.database.Database
import org.ktorm.support.postgresql.bulkInsertOrUpdate

class DefaultStateSaver(val database: Database) : StateSaver {

    override fun save(state: State) {
        database.bulkInsertOrUpdate(States) {
            item {
                set(it.status, StateStatus.new)

                set(it.visited, state.visited)
                set(it.reachable, state.reachable)

                set(it.atk, state.atk)
                set(it.def, state.def)
                set(it.hp, state.hp)

                set(it.blue_keys, state.blueKeys)
                set(it.crimson_keys, state.crimsonKeys)
                set(it.platinum_keys, state.platinumKeys)
                set(it.violet_keys, state.violetKeys)
                set(it.yellow_keys, state.yellowKeys)

                set(it.moves, state.moves)
            }
            onConflict(
                States.visited,
                States.reachable,

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