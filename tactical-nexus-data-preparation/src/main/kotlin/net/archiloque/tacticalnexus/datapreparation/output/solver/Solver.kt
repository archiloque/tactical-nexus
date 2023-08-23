package net.archiloque.tacticalnexus.datapreparation.output.solver

import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import java.nio.file.Paths
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.Item
import net.archiloque.tacticalnexus.datapreparation.input.entities.Stat
import net.archiloque.tacticalnexus.datapreparation.input.level.TowerLevel


class Solver(
    val enemies: List<Enemy>,
    val items: List<Item>,
    val towerLevels: List<TowerLevel>,
    val stats: List<Stat>,
) {
    private val generatedPath = Paths.get("../tactical-nexus-solver/src/main/kotlin")

    companion object {
        val arrayOf = MemberName("kotlin", "arrayOf")
        const val ENTITIES_PACKAGE = "net.archiloque.tacticalnexus.solver.entities"
        const val INPUT_PACKAGE = "net.archiloque.tacticalnexus.solver.input"
        fun arrayOf(typeName: TypeName): ParameterizedTypeName {
            return Array::class.asClassName().parameterizedBy(typeName)
        }
    }

    fun generate() {
        println("Generating data for solver")
        Items.generate(items, generatedPath)
        Levels.generate(towerLevels, items, enemies, stats, generatedPath)
    }
}
