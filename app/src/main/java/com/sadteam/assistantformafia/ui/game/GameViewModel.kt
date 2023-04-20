package com.sadteam.assistantformafia.ui.game

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationEvent
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {
    var state = mutableStateOf(GameState())
        private set

    fun onEvent(event: GameEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is GameEvent.InitGame ->
                    initGame(event.initialState)
            }
        }
    }

    private fun initGame(initialState: GameCreationState) {
        val players = mutableMapOf<Player, Role?>()
        for ((index, player) in initialState.players.withIndex()) {
            val copiedPlayer = player.copy()
            if (copiedPlayer.name.isEmpty()) {
                copiedPlayer.name = "${context.resources.getString(R.string.player)} " +
                        "${index + 1}"
            }
            players[copiedPlayer] = null
        }
        state.value = state.value.copy(
            players = players,
            rolesCount = initialState.roles,
        )
    }
}