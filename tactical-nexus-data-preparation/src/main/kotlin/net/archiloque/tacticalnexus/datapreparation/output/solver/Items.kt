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
import net.archiloque.tacticalnexus.datapreparation.input.entities.Item

class Items {

    companion object {
        fun generate(items: List<Item>, generatedPath: Path) {
            println("Generating items")
            val itemClass = ClassName(Solver.ENTITIES_PACKAGE, "Item")

            val itemsCompanion = TypeSpec
                .companionObjectBuilder()
            for (item in items.sortedBy { it.identifier }) {
                val initializerCode = CodeBlock.Builder().add(
                    "%T(%S, ${item.atk}, ${item.def}, ${item.hp})",
                    itemClass,
                    item.identifier
                ).build()
                itemsCompanion.addProperty(
                    PropertySpec
                        .builder(item.identifier, itemClass)
                        .initializer(
                            initializerCode
                        )
                        .build()
                )
            }

            val itemsArrayCode = CodeBlock.Builder().add("%M(", Solver.arrayOf)
            for (item in items) {
                itemsArrayCode.add(
                    "${item.identifier}, ",
                )
            }
            itemsArrayCode.add(")")
            val itemsProperty = PropertySpec
                .builder("items", Array::class.asClassName().parameterizedBy(itemClass))
                .initializer(itemsArrayCode.build())
                .build()

            itemsCompanion.addProperty(
                itemsProperty
            )

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
    }

}
