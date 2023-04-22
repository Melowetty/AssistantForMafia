package com.sadteam.assistantformafia.ui.game

import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState

sealed class GameEvent {
    data class InitGame(val initialState: GameCreationState): GameEvent()
    object NextSelectRole: GameEvent()
    data class SetRole(val player: Player, val role: Role?): GameEvent()
    data class ClearRole(val player: Player): GameEvent()
}