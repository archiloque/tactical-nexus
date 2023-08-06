package net.archiloque.tacticalnexussataprepartion.formats.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val itemCustomFields: ItemCustomFields,
) : Entity


@Serializable
data class ItemCustomFields(
    @Required val item: String,
)
