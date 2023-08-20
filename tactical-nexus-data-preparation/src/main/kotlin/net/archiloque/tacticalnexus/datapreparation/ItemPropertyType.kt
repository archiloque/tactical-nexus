package net.archiloque.tacticalnexus.datapreparation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ItemPropertyType {
    @SerialName("Pt")
    Points,

    @SerialName("%")
    Percents
}