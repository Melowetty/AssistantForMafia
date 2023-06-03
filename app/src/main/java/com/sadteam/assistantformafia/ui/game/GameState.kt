package com.sadteam.assistantformafia.ui.game

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
import java.util.*

data class GameState(
    val gameId: UUID? = null,
    val players: SnapshotStateList<Player> = mutableStateListOf(),
    val rolesCount: List<Role> = listOf(),
    val isActive: Boolean = false,
    val distributionOfRoles: DistributionOfRolesState = DistributionOfRolesState(),
    val nightSelectState: NightSelectState = NightSelectState(),
    val dayVotingState: DayVotingState = DayVotingState(),
    val handshakeState: HandshakeState = HandshakeState(),
    val endGameState: EndGameState = EndGameState(),
)

data class DistributionOfRolesState(
    val targetRole: Role? = null,
    val nextRole: Role? = null,
    val currentCount: Int = 0,
    val maxCount: Int = 0,
    val queuePlayers: SnapshotStateList<Player> = mutableStateListOf(),
    val indexTargetRole: Int = 0,
    val canNext: Boolean = false,
    val isEnd: Boolean = false,
)

data class NightSelectState(
    val targetRole: Role? = null,
    val nextRole: Role? = null,
    val queuePlayers: SnapshotStateList<Player> = mutableStateListOf(),
    val targetPlayerIndex: Int = -1,
    val indexTargetRole: Int = 0,
    val canNext: Boolean = false,
    val isEnd: Boolean = false,
)

data class DayVotingState(
    val players: SnapshotStateList<Player> = mutableStateListOf(),
    val countLivePlayers: Int = 0,
    val countPlayersWhoCanVote: Int = 0,
    val totalVoices: Int = 0,
    val canKick: Boolean = false,
    val isHandshake: Boolean = false,
    val isEnd: Boolean = false,
    val gameIsEnd: Boolean = false,
)

data class HandshakeState(
    val players: SnapshotStateList<Player> = mutableStateListOf(),
    val canKick: Boolean = false,
    val targetPlayerIndex: Int = -1,
    val gameIsEnd: Boolean = false,
)

data class EndGameState(
    val roleWin: Role? = null
)