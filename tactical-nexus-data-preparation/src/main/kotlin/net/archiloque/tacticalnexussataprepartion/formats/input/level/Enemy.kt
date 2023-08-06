package net.archiloque.tacticalnexussataprepartion.formats.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexussataprepartion.EnemyType

@Serializable
class Enemy(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val enemyCustomFields: EnemyCustomFields,
) : Entity


@Serializable
data class EnemyCustomFields(
    @Required val type: EnemyType,
    @Required val level: Int,
)
