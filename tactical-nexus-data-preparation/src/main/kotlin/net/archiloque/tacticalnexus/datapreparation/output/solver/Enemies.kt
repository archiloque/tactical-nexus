package net.archiloque.tacticalnexus.datapreparation.output.solver;

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import java.nio.file.Path
import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.datapreparation.EnemyType
import net.archiloque.tacticalnexus.datapreparation.input.entities.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.entities.Item

class Enemies {

    companion object {
        fun generate(enemies: List<Enemy>, items: List<Item>, generatedPath: Path) {
            println("Generating enemies")
            val enemyClass = ClassName(Solver.ENTITIES_PACKAGE, "Enemy")

            val file = FileSpec
                .builder(Solver.INPUT_PACKAGE, "Enemies")
                .addType(
                    TypeSpec
                        .classBuilder("Enemies")
                        .addAnnotation(Generated::class)
                        .addType(
                            TypeSpec
                                .companionObjectBuilder()
                                .addProperty(
                                    enemyBuilder(enemies, items, enemyClass, "fighters", EnemyType.fighter)
                                )
                                .addProperty(
                                    enemyBuilder(enemies, items, enemyClass, "rangers", EnemyType.ranger)
                                )
                                .build()
                        )
                        .build()
                )
            file.build().writeTo(generatedPath)
        }

        private fun enemyBuilder(
            enemies: List<Enemy>,
            items: List<Item>,
            enemyClass: ClassName,
            enemyNames: String,
            enemyType: EnemyType,
        ): PropertySpec {
            val enemiesArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)
            val maxEnemyLevel = enemies.filter { it.type == enemyType }.maxOf { it.level }
            for (level in 0..maxEnemyLevel) {
                val enemy = enemies.find { (it.type == enemyType) && (it.level == level) }
                if (enemy == null) {
                    enemiesArrayCode.add("null, ")
                } else {
                    val itemIndex = items.indexOfFirst { it.identifier == enemy.drop }
                    enemiesArrayCode.add(
                        "%T(${enemy.hp}, ${enemy.atk}, ${enemy.def}, ${enemy.exp}, Items.${enemy.drop}), ",
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

}
