package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Entity {
    @SerialName("x")
    @Required
    val x: Int

    @SerialName("y")
    @Required
    val y: Int
}
