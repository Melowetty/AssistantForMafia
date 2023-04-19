package com.sadteam.assistantformafia.ui.gamecreation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.data.repository.RoleRepository
import com.sadteam.assistantformafia.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Math.min
import javax.inject.Inject

@HiltViewModel
class GameCreationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val roleRepository: RoleRepository
    ): ViewModel() {
    var state = mutableStateOf(GameCreationState())
        private set
    private val observer = Observer<List<Role>> { roles ->
        val rolesFromDb: MutableMap<Role, Int> = mutableMapOf()
        var distributedPlayers: Int = 0
        for (role in roles) {
            rolesFromDb[role] = role.min
            distributedPlayers += role.min
        }
        state.value = state.value.copy(
            roles = rolesFromDb,
            distributedPlayers = distributedPlayers
        )
    }

    init {
        viewModelScope.launch(Dispatchers.Main) {
            roleRepository.getAllRoles().observeForever(observer)
        }
    }

    override fun onCleared() {
        super.onCleared()
        roleRepository.getAllRoles().removeObserver(observer)
    }

    fun onEvent(event: GameCreationEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                GameCreationEvent.IncrementPlayers ->
                    increasePlayersCount()

                GameCreationEvent.DecrementPlayers ->
                    decreasePlayersCount()

                is GameCreationEvent.SetPlayerName ->
                    setPlayerName(event.pos, event.newName)

                is GameCreationEvent.DeletePlayer ->
                    deletePlayer(event.pos)

                is GameCreationEvent.DecrementRole -> {
                    val currentValue = state.value.roles[event.role]
                    decreaseRoleCount(event.role, currentValue!!)
                }

                is GameCreationEvent.IncrementRole -> {
                    val currentValue = state.value.roles[event.role]
                    increaseRoleCount(event.role, currentValue!!)
                }
            }
        }
    }

    private fun decreaseRoleCount(role: Role, currentValue: Int) {
        if (role.min < currentValue) {
            val roles = state.value.roles.toMutableMap()
            roles[role] = currentValue - 1
            state.value = state.value.copy(
                roles = roles,
                distributedPlayers = state.value.distributedPlayers - 1
            )
        }
    }

    private fun increaseRoleCount(role: Role, currentValue: Int) {
        if (Utils.getRoleCountLimit(role, currentValue, state.value.players.size, state.value.distributedPlayers) > currentValue) {
            val roles = state.value.roles.toMutableMap()
            roles[role] = currentValue + 1
            state.value = state.value.copy(
                roles = roles,
                distributedPlayers = state.value.distributedPlayers + 1
            )
        }
    }

    private fun increasePlayersCount() {
        val players = state.value.players.toMutableList()
        players.add(Player())
        state.value = state.value.copy(
            players = players
        )
    }

    private fun deletePlayer(pos: Int) {
        if (state.value.players.size <= 6) return
        val players = state.value.players.toMutableList()
        players.removeAt(pos)
        state.value = state.value.copy(
            players = players
        )
    }

    private fun setPlayerName(pos: Int, newName: String) {
        val players = state.value.players.toMutableList()
        players[pos].name = newName
        state.value = state.value.copy(
            players = players
        )
    }

    private fun decreasePlayersCount() {
        if (state.value.players.size <= 6) return
        val players = state.value.players.toMutableList()
        players.removeLast()
        state.value = state.value.copy(
            players = players
        )
    }
}