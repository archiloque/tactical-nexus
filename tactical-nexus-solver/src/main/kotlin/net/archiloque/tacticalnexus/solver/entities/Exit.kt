package net.archiloque.tacticalnexus.solver.entities

import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State

class Exit() : Entity() {
    companion object {
        val instance = Exit()
    }

    override fun getType(): EntityType {
        return EntityType.Exit
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        println("Win !")
        println(state)
        exitProcess(0)
    }
}