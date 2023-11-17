package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.enums.KeyOrDoorColor

@Serializable
sealed interface KeyOrDoor {
    fun color(): KeyOrDoorColor
}

@Serializable
data class KeyOrDoorCustomFields(
    @SerialName("key_or_door_color") @Required val color: KeyOrDoorColor,
)
