package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface KeyOrDoor {
    fun color(): String
}

@Serializable
data class KeyOrDoorCustomFields(
    @SerialName("key_or_door_color") @Required val color: String,
)
