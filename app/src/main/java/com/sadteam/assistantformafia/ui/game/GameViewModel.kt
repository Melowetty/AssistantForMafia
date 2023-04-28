package com.sadteam.assistantformafia.ui.game

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Possibility
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
                is GameEvent.StartGame -> {
                    initNightVoting()
                }
                is GameEvent.SelectNightTarget ->
                    selectNightTarget(event.index)
                is GameEvent.ClearNightTarget ->
                    clearNightTarget(event.index)
                is GameEvent.NextNightSelect ->
                    nextNightSelect()
            }
        }
    }

    private fun initGame(initialState: GameCreationState) {
        val players = mutableListOf<Player>()
        for ((index, player) in initialState.players.withIndex()) {
            val copiedPlayer = player.copy()
            if (copiedPlayer.name.value == "") {
                copiedPlayer.name.value = "${context.resources.getString(R.string.player)} " +
                        "${index + 1}"
            }
            copiedPlayer.role = null
            copiedPlayer.isSelected = false
            players.add(copiedPlayer)
        }
        val roles = initialState.roles.filter { it.value != 0 }
        state.value = state.value.copy(
            players = players,
            rolesCount = roles,
            distributionOfRoles = DistributionOfRolesState(
                targetRole = roles.keys.elementAt(0),
                nextRole = roles.keys.elementAt(1),
                queuePlayers = players,
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
            it.role == null
        }.map { player ->
            player.copy(isSelected = false)
        }

        state.value = state.value.copy(
            distributionOfRoles = state.value.distributionOfRoles.copy(
                targetRole = nextRole,
                nextRole = newNextRole,
                maxCount = newMax,
                queuePlayers = queuePlayers,
                indexTargetRole = newIndexTargetRole,
                currentCount = 0,
                canNext = false,
            )
        )
    }

    private fun setRole(player: Player, role: Role?) {
        val currentValue = state.value.distributionOfRoles.currentCount
        if (currentValue == state.value.distributionOfRoles.maxCount && role != null) return
        var addition = 0
        val players = state.value.players.toMutableList()
        val indexInPlayers = players.indexOf(player)
        val indexInQueue = state.value.distributionOfRoles.queuePlayers.indexOf(player)
        players[indexInPlayers].apply {
            this.role = role
        }
        val playersChecked = state.value.distributionOfRoles.queuePlayers.toMutableList()
        if(role == null) {
            addition = -1
            playersChecked[indexInQueue].apply {
                isSelected = false
            }
        } else {
            addition = 1
            playersChecked[indexInQueue].apply {
                isSelected = true
            }
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

    private fun initNightVoting() {
        val roles = state.value.rolesCount.filter { it.key.possibilities.first() != Possibility.NONE }
        val targetRole = roles.keys.elementAt(0)
        var isEnd = false
        val nextRole = if (roles.size == 1) null else roles.keys.elementAt(1)
        if (nextRole == null) isEnd = true
        val players = state.value.players
        val queuePlayers = players.filter { it.role != targetRole }
        state.value = state.value.copy(
            nightSelectState = NightSelectState(
                targetRole = targetRole,
                nextRole = nextRole,
                queuePlayers = queuePlayers,
                isEnd = isEnd,
            ),
        )
    }

    private fun selectNightTarget(index: Int) {
        state.value = state.value.copy(
            nightSelectState = state.value.nightSelectState.copy(
                targetPlayerIndex = index,
                canNext = true,
            )
        )
    }

    private fun clearNightTarget(index: Int) {
        state.value = state.value.copy(
            nightSelectState = state.value.nightSelectState.copy(
                targetPlayerIndex = -1,
                canNext = false,
            )
        )
    }

    private fun nextNightSelect() {
        val (targetRole, nextRole, _, targetPlayer, indexTargetRole, _, _, actions) =
            state.value.nightSelectState
        val newActions = actions.toMutableMap()
        val roles = state.value.rolesCount.filter { it.key.possibilities.first() != Possibility.NONE }
        val newIndexTargetRole = indexTargetRole + 1
        val newNextRole = if(roles.size == newIndexTargetRole + 1) null
        else roles.keys.elementAt(newIndexTargetRole + 1)
        if(newNextRole == null) {
            state.value = state.value.copy(
                nightSelectState = state.value.nightSelectState.copy(
                    isEnd = true
                )
            )
        }
        val players = state.value.players
        val queuePlayers = players.filter { it.role != nextRole }
        state.value = state.value.copy(
            nightSelectState = state.value.nightSelectState.copy(
                targetRole = nextRole,
                targetPlayerIndex = -1,
                nextRole = newNextRole,
                queuePlayers = queuePlayers,
                indexTargetRole = newIndexTargetRole,
                canNext = false,
                actions = newActions,
            )
        )
    }

//    private fun actionToPlayer(player: Player, currentPlayerState: PlayerState, targetRole: Role): PlayerState {
//        when (targetRole.possibilities.first()) {
//            Possibility.KILL -> {
//                if (currentPlayerState == PlayerState.HEALED) return PlayerState.HEALED
//                else return PlayerState.KILLED
//            }
//        }
//    }
}