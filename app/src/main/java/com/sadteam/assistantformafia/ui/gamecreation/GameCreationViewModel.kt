package com.sadteam.assistantformafia.ui.gamecreation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadteam.assistantformafia.data.db.AppDatabase
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.data.repository.RoleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class GameCreationState(
    val players: Int = 4,
    val roles: Map<Role, Int> = mapOf(),
    val distributedPlayers: Int = 0,
)

class GameCreationViewModel(private val context: Context): ViewModel() {
    private val _state = mutableStateOf(GameCreationState())
    private val roleRepository: RoleRepository
    val state: State<GameCreationState> = _state
    private val observer = Observer<List<Role>> { roles ->
        val rolesFromDb: MutableMap<Role, Int> = mutableMapOf()
        var distributedPlayers: Int = 0
        for (role in roles) {
            rolesFromDb[role] = role.min
            distributedPlayers += role.min
        }
        _state.value = state.value.copy(
            roles = rolesFromDb,
            distributedPlayers = distributedPlayers
        )
    }

    init {
        val appDatabase = AppDatabase.getDatabase(context)
        val rolesDao = appDatabase.getRolesDao()
        roleRepository = RoleRepository(rolesDao)
        roleRepository.getAllRoles().observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        roleRepository.getAllRoles().removeObserver(observer)
    }

    sealed class UIEvent {
        object IncrementPlayers : UIEvent()
        object DecrementPlayers : UIEvent()
        data class DecrementRole(val role: Role): UIEvent()
        data class IncrementRole(val role: Role): UIEvent()
    }

    fun onEvent(event: UIEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                UIEvent.IncrementPlayers ->
                    _state.value = state.value.copy(
                        players = state.value.players + 1
                    )

                UIEvent.DecrementPlayers ->
                    if (state.value.players > 4) {
                        _state.value = state.value.copy(
                            players = state.value.players - 1
                        )
                    }
                is UIEvent.DecrementRole -> {
                    val currentValue = state.value.roles[event.role]
                    decreaseRoleCount(event.role, currentValue!!)
                }

                is UIEvent.IncrementRole -> {
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
            _state.value = state.value.copy(
                roles = roles,
                distributedPlayers = state.value.distributedPlayers - 1
            )
        }
    }

    private fun increaseRoleCount(role: Role, currentValue: Int) {
        if (state.value.distributedPlayers < state.value.players
            && currentValue < role.max) {
            val roles = state.value.roles.toMutableMap()
            roles[role] = currentValue + 1
            _state.value = state.value.copy(
                roles = roles,
                distributedPlayers = state.value.distributedPlayers + 1
            )
        }
    }
}