package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role

data class GameCreationState(
    val players: SnapshotStateList<Player> = mutableStateListOf(),
    val roles: List<Role> = listOf(),
    val distributedPlayers: Int = 0,
    val canStart: Boolean = false,
    val rolesIsDistributed: Boolean = false,
)