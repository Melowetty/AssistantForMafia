package com.sadteam.assistantformafia.ui.gamecreation

import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.utils.MIN_PLAYERS_COUNT

data class GameCreationState(
    val players: List<Player> = List(MIN_PLAYERS_COUNT) { Player() },
    val roles: Map<Role, Int> = mapOf(),
    val distributedPlayers: Int = 0,
    val canStart: Boolean = false,
)