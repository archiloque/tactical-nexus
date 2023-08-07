package net.archiloque.tacticalnexus.datapreparation.output

import com.squareup.kotlinpoet.FileSpec
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.Item
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import java.nio.file.Paths

class Solver(enemies: List<Enemy>, items: List<Item>, levels: List<Level>) {
    fun generate() {
        val file = FileSpec.builder("net.archiloque.tacticalnexus.solver.input", "Enemies")
        file.build().writeTo(Paths.get("../tactical-nexus-solver/src/main/generated"))
    }

}
