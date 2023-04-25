package com.sadteam.assistantformafia.ui.game

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
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
                is GameEvent.NextSelectRole ->
                    nextSelectRole()
                is GameEvent.SetRole ->
                    setRole(event.player, event.role)
                is GameEvent.ClearRole ->
                    setRole(event.player, null)
                is GameEvent.StartGame ->
                    startGame()
            }
        }
    }

    private fun initGame(initialState: GameCreationState) {
        val players = mutableMapOf<Player, Role?>()
        for ((index, player) in initialState.players.withIndex()) {
            val copiedPlayer = player.copy()
            if (copiedPlayer.name.value == "") {
                copiedPlayer.name.value = "${context.resources.getString(R.string.player)} " +
                        "${index + 1}"
            }
            players[copiedPlayer] = null
        }
        val roles = initialState.roles.filter { it.value != 0 }
        state.value = state.value.copy(
            players = players,
            rolesCount = roles,
            distributionOfRoles = DistributionOfRolesState(
                targetRole = roles.keys.elementAt(0),
                nextRole = roles.keys.elementAt(1),
                queuePlayers = players.mapValues { (_, _) -> false },
                maxCount = roles.values.first(),
                indexTargetRole = 0,
            )
        )
    }

    private fun nextSelectRole() {
        val (targetRole, nextRole, _, _, _, indexTargetRole, _) =
            state.value.distributionOfRoles
        val newIndexTargetRole = indexTargetRole + 1
        val newNextRole = if(state.value.rolesCount.size == newIndexTargetRole + 1) null
            else state.value.rolesCount.keys.elementAt(newIndexTargetRole + 1)
        if(newNextRole == null) {
            state.value = state.value.copy(
                distributionOfRoles = state.value.distributionOfRoles.copy(
                    isEnd = true
                )
            )
        }
        val newMax = state.value.rolesCount[nextRole] ?: 0
        val queuePlayers = state.value.players.filter {
            it.value == null
        }.mapValues { (player, _) -> false }

        state.value = state.value.copy(
            distributionOfRoles = state.value.distributionOfRoles.copy(
                targetRole = nextRole,
                nextRole = newNextRole,
                maxCount = newMax,
                queuePlayers = queuePlayers,
                indexTargetRole = newIndexTargetRole,
                currentCount = 0,
            )
        )
    }

    private fun setRole(player: Player, role: Role?) {
        val currentValue = state.value.distributionOfRoles.currentCount
        if (currentValue == state.value.distributionOfRoles.maxCount && role != null) return
        var addition = 0
        val players = state.value.players.toMutableMap()
        players[player] = role
        val playersChecked = state.value.distributionOfRoles.queuePlayers.toMutableMap()
        if(role == null) {
            addition = -1
            playersChecked[player] = false
        } else {
            addition = 1
            playersChecked[player] = true
        }
        var canNext = false
        if (currentValue + addition == state.value.distributionOfRoles.maxCount) canNext = true
        state.value = state.value.copy(
            players = players,
            distributionOfRoles = state.value.distributionOfRoles.copy(
                currentCount = currentValue + addition,
                queuePlayers = playersChecked,
                canNext = canNext
            )
        )
    }

    private fun startGame() {
        state.value = state.value.copy(
            distributionOfRoles = DistributionOfRolesState()
        )
    }
}