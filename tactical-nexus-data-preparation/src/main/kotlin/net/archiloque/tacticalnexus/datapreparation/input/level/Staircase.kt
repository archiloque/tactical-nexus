package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Staircase(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val staircaseCustomFields: StaircaseCustomFields,
) : Entity

@Serializable
data class StaircaseCustomFields(
    @Required val direction: StaircaseDirection,
)

@Serializable
enum class StaircaseDirection() {
    up,
    down
}
