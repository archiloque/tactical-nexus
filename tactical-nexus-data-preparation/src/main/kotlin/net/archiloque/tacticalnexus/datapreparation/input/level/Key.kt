package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Key(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val keyOrDoorCustomFields: KeyOrDoorCustomFields,
) : Entity, KeyOrDoor {
    override fun color(): String {
        return keyOrDoorCustomFields.color
    }
}