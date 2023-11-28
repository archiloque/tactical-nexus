package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.enums.Direction

@Serializable
data class OneWay(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val oneWayCustomFields: OneWayCustomFields,
) : Entity {
    fun direction(): Direction {
        return oneWayCustomFields.direction
    }
}


@Serializable
data class OneWayCustomFields(
    @Required val direction: Direction,
)
