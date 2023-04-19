package com.sadteam.assistantformafia.ui.gamecreation

import com.sadteam.assistantformafia.data.models.Role

sealed class GameCreationEvent {
    object IncrementPlayers : GameCreationEvent()
    object DecrementPlayers : GameCreationEvent()
    data class SetPlayerName(val pos: Int, val newName: String): GameCreationEvent()
    data class DeletePlayer(val pos: Int) : GameCreationEvent()
    data class DecrementRole(val role: Role): GameCreationEvent()
    data class IncrementRole(val role: Role): GameCreationEvent()
}