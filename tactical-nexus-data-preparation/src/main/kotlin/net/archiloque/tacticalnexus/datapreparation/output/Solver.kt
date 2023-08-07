package net.archiloque.tacticalnexus.datapreparation.output

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import net.archiloque.tacticalnexus.datapreparation.EnemyType
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.Item
import net.archiloque.tacticalnexus.datapreparation.input.level.Level
import java.nio.file.Paths
import javax.annotation.processing.Generated

class Solver(val enemies: List<Enemy>, items: List<Item>, levels: List<Level>) {
    fun generate() {
        val enemyClass = ClassName("net.archiloque.tacticalnexus.solver", "Enemy")
        val arrayOf = MemberName("kotlin", "arrayOf")

        val file = FileSpec
            .builder("net.archiloque.tacticalnexus.solver.input", "Enemies")
            .addType(
                TypeSpec
                    .classBuilder("Enemies")
                    .addAnnotation(Generated::class)
                    .addProperty(
                        enemyBuilder(arrayOf, enemyClass, "fighters", EnemyType.fighter)
                    )
                    .addProperty(
                        enemyBuilder(arrayOf, enemyClass, "rangers", EnemyType.ranger)
                    )
                    .build()
            )
        file.build().writeTo(Paths.get("../tactical-nexus-solver/src/main/generated"))
    }

    private fun enemyBuilder(
        arrayOf: MemberName,
        enemyClass: ClassName,
        enemyNames: String,
        enemyType: EnemyType
    ): PropertySpec {
        val enemiesArrayCode = CodeBlock.Builder().add("%M(", arrayOf)
        val maxEnemyLevel = enemies.filter { it.type == enemyType }.maxOf { it.level }
        for (level in 0..maxEnemyLevel) {
            val enemy = enemies.find { (it.type == enemyType) && (it.level == level) }
            if (enemy == null) {
                enemiesArrayCode.add("null, ")
            } else {
                enemiesArrayCode.add(
                    "%T(${enemy.hp}, ${enemy.atk}, ${enemy.def}, ${enemy.exp}, \"\"), ",
                    enemyClass
                )
            }
        }
        enemiesArrayCode.add(")")
        return PropertySpec
            .builder(enemyNames, Array::class.asClassName().parameterizedBy(enemyClass.copy(nullable = true)))
            .initializer(enemiesArrayCode.build())
            .build()
    }

}
