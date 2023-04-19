package com.sadteam.assistantformafia.ui.gamecreation

import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role

data class GameCreationState(
    val players: List<Player> = List(6) { Player() },
    val roles: Map<Role, Int> = mapOf(),
    val distributedPlayers: Int = 0,
)