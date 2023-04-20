package com.sadteam.assistantformafia.ui.game

import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState

sealed class GameEvent {
    data class InitGame(val initialState: GameCreationState): GameEvent()
}