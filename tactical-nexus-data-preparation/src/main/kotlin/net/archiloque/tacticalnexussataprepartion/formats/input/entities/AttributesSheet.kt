package net.archiloque.tacticalnexussataprepartion.formats.input.entities

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Attributes")
data class AttributesSheet(
    @Required override val name: String,
) : Sheet
