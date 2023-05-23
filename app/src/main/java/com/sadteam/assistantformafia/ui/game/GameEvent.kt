package com.sadteam.assistantformafia.ui.game

import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState

sealed class GameEvent {
    data class InitGame(val initialState: GameCreationState): GameEvent()
    object NextSelectRole: GameEvent()
    data class SetRole(val player: Player, val role: Role?): GameEvent()
    data class ClearRole(val player: Player): GameEvent()
    object StartGame: GameEvent()
    object EndGame: GameEvent()
    object StartNightVoting: GameEvent()
    object NextNightSelect: GameEvent()
    data class SelectNightTarget(val index: Int): GameEvent()
    object ClearNightTarget: GameEvent()
    object StartDayVoting: GameEvent()
    data class IncreaseVoices(val playerIndex: Int): GameEvent()
    data class DecreaseVoices(val playerIndex: Int): GameEvent()
    object KickPlayer: GameEvent()
    object NextRound: GameEvent()
    object StartHandshake: GameEvent()
    data class SelectHandshakeTarget(val playerIndex: Int): GameEvent()
    object ClearHandshakeTarget: GameEvent()
    object KickHandshakeTarget: GameEvent()
}