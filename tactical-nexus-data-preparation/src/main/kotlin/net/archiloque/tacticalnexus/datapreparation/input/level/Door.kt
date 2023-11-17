package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.enums.KeyOrDoorColor

@Serializable
data class Door(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val keyOrDoorCustomFields: KeyOrDoorCustomFields,
) : Entity, KeyOrDoor {
    override fun color(): KeyOrDoorColor {
        return keyOrDoorCustomFields.color
    }
}
