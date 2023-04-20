package com.sadteam.assistantformafia.ui.game

import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role

data class GameState(
    val players: Map<Player, Role?> = mapOf(),
    val rolesCount: Map<Role, Int> = mapOf(),
)