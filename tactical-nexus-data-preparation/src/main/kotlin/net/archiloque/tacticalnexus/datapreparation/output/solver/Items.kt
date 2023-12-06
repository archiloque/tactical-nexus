package net.archiloque.tacticalnexus.datapreparation.output.solver

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.nio.file.Path
import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.datapreparation.input.entities.ItemDefault

class Items {
    companion object {
        private val itemClass = ClassName(Solver.INPUT_ENTITIES_PACKAGE, "Item")

        fun generate(items: List<ItemDefault>, generatedPath: Path) {
            println("Generating items")

            val itemsCompanion = TypeSpec
                .companionObjectBuilder()
            for (item in items.sortedBy { it.identifier }) {
                generateItem(item, "", itemsCompanion, KModifier.PUBLIC)
            }

            val file = FileSpec
                .builder(Solver.INPUT_PACKAGE, "Items")
                .addType(
                    TypeSpec
                        .classBuilder("Items")
                        .addAnnotation(Generated::class)
                        .addType(
                            itemsCompanion
                                .build()
                        )
                        .build()
                )
            file.build().writeTo(generatedPath)
        }

        public fun generateItem(
            item: ItemDefault,
            prefix: String,
            typeSpecBuilder: TypeSpec.Builder,
            modifier: KModifier,
        ) {
            val initializerCode = CodeBlock.Builder().add(
                "%T(%S, ${item.atk}, ${item.def}, ${item.expBonusAdd}, ${item.expBonusMul}, ${item.hp}, ${item.hpBonusAdd}, ${item.hpBonusMul})",
                itemClass,
                item.name,
            ).build()
            typeSpecBuilder.addProperty(
                PropertySpec
                    .builder("${prefix}${item.identifier}", itemClass, modifier)
                    .initializer(
                        initializerCode
                    )
                    .build()
            )
        }
    }

}
