package com.sadteam.assistantformafia.ui.game

import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role

data class GameState(
    val players: List<Player> = listOf(),
    val rolesCount: Map<Role, Int> = mapOf(),
    val isActive: Boolean = false,
    val distributionOfRoles: DistributionOfRolesState = DistributionOfRolesState(),
    val nightSelectState: NightSelectState = NightSelectState(),
    val dayVotingState: DayVotingState = DayVotingState(),
    val endGameState: EndGameState = EndGameState(),
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
)

data class DayVotingState(
    val players: List<Player> = listOf(),
    val countLivePlayers: Int = 0,
    val countPlayersWhoCanVote: Int = 0,
    val totalVoices: Int = 0,
    val canKick: Boolean = false,
    val isEnd: Boolean = false,
)

data class EndGameState(
    val roleWin: Role? = null
)