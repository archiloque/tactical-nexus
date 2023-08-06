package net.archiloque.tacticalnexussataprepartion.formats.input.entities

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Item")
data class ItemSheet(
    @Required override val name: String,
    @Required @SerialName("lines") val items: List<Item>,
) : Sheet

@Serializable
data class Item(
    @Required @SerialName("Name") val name: String,
    @Required @SerialName("ATK") val atk: Int,
    @Required @SerialName("DEF") val def: Int,
    @Required @SerialName("HP") val hp: Int,
)