package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.ui.graphics.ImageBitmap
import com.sadteam.assistantformafia.data.models.Role

sealed class GameCreationEvent {
    object IncrementPlayers : GameCreationEvent()
    object DecrementPlayers : GameCreationEvent()
    data class SetPlayerName(val pos: Int, val newName: String): GameCreationEvent()
    data class SetPlayerImage(val pos: Int, val image: ImageBitmap?): GameCreationEvent()
    data class DeletePlayer(val pos: Int) : GameCreationEvent()
    data class DecrementRole(val role: Role): GameCreationEvent()
    data class IncrementRole(val role: Role): GameCreationEvent()
}