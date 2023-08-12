package net.archiloque.tacticalnexus.datapreparation.output.solver

import com.squareup.kotlinpoet.MemberName
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.Item
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import java.nio.file.Paths


class Solver(val enemies: List<Enemy>, val items: List<Item>, val levels: List<Level>) {
    private val generatedPath = Paths.get("../tactical-nexus-solver/src/main/generated")

    companion object {
        val arrayOf = MemberName("kotlin", "arrayOf")
        const val ENTITIES_PACKAGE = "net.archiloque.tacticalnexus.solver.entities"
        const val INPUT_PACKAGE = "net.archiloque.tacticalnexus.solver.input"
    }

    fun generate() {
        println("Generating data for solver")
        Items.generate(items, generatedPath)
        Enemies.generate(enemies, items, generatedPath)
        Levels.generate(levels, generatedPath)
    }
}
