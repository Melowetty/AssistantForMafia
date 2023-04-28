package com.sadteam.assistantformafia.ui.game

import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.PlayerState
import com.sadteam.assistantformafia.data.models.Role

data class GameState(
    val players: List<Player> = listOf(),
    val rolesCount: Map<Role, Int> = mapOf(),
    val distributionOfRoles: DistributionOfRolesState = DistributionOfRolesState(),
    val nightSelectState: NightSelectState = NightSelectState(),
)

data class DistributionOfRolesState(
    val targetRole: Role? = null,
    val nextRole: Role? = null,
    val currentCount: Int = 0,
    val maxCount: Int = 0,
    val queuePlayers: List<Player> = listOf(),
    val indexTargetRole: Int = 0,
    val canNext: Boolean = false,
    val isEnd: Boolean = false,
)

data class NightSelectState(
    val targetRole: Role? = null,
    val nextRole: Role? = null,
    val queuePlayers: List<Player> = listOf(),
    val targetPlayerIndex: Int = -1,
    val indexTargetRole: Int = 0,
    val canNext: Boolean = false,
    val isEnd: Boolean = false,
    val actions: Map<Player, PlayerState> = mapOf()
)