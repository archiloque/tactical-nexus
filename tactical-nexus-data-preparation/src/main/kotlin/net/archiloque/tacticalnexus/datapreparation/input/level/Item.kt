package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.EnemyType

@Serializable
data class Item(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val itemCustomFields: ItemCustomFields,
) : Entity {
    fun identifier(): String {
        return itemCustomFields.item
    }
}


@Serializable
data class ItemCustomFields(
    @Required val item: String,
)
