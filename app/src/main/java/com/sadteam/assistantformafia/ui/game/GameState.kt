package com.sadteam.assistantformafia.ui.game

import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role

data class GameState(
    val players: Map<Player, Role?> = mapOf(),
    val rolesCount: Map<Role, Int> = mapOf(),
    val distributionOfRoles: DistributionOfRolesState = DistributionOfRolesState(),
    val nightSelectState: NightSelectState = NightSelectState(),
)

data class DistributionOfRolesState(
    val targetRole: Role? = null,
    val nextRole: Role? = null,
    val currentCount: Int = 0,
    val maxCount: Int = 0,
    val queuePlayers: Map<Player, Boolean> = mapOf(),
    val indexTargetRole: Int = 0,
    val canNext: Boolean = false,
    val isEnd: Boolean = false,
)

data class NightSelectState(
    val targetRole: Role? = null,
    val nextRole: Role? = null,
    val queuePlayers: Map<Player, Role?> = mapOf(),
    val targetPlayer: Player? = null,
    val indexTargetRole: Int = 0,
    val canNext: Boolean = false,
    val isEnd: Boolean = false,
)