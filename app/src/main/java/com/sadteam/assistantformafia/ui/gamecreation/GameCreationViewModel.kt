package com.sadteam.assistantformafia.ui.gamecreation

import android.content.Context
import android.os.Build
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sadteam.assistantformafia.data.db.AppDatabase
import com.sadteam.assistantformafia.data.db.RolesDao
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.data.repository.RoleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class GameCreationState(
    val players: Int = 4,
    val roles: Map<Role, Int> = mapOf(),
)

class GameCreationViewModel(private val context: Context): ViewModel() {
    private val _state = mutableStateOf(GameCreationState())
    private val roleRepository: RoleRepository
    val state: State<GameCreationState> = _state

    init {
        val appDatabase = AppDatabase.getDatabase(context)
        val rolesDao = appDatabase.getRolesDao()
        roleRepository = RoleRepository(rolesDao)

        viewModelScope.launch(Dispatchers.IO) {
            val roles = roleRepository.getAllEvents()
            val rolesFromDb: MutableMap<Role, Int> = mutableMapOf()
            for (role in roles) {
                rolesFromDb[role] = 0
            }
            _state.value = state.value.copy(
                roles = rolesFromDb
            )
        }
    }

    sealed class UIEvent {
        object IncrementPlayers : UIEvent()
        object DecrementPlayers : UIEvent()
        class DecrementRole(slug: String): UIEvent()
        class IncrementRole(slug: String): UIEvent()
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            UIEvent.IncrementPlayers ->
                _state.value = state.value.copy(
                    players = state.value.players + 1
                )

            UIEvent.DecrementPlayers ->
                if (_state.value.players > 4) {
                    _state.value = state.value.copy(
                        players = state.value.players - 1
                    )
                }
//            is UIEvent.DecrementRole ->
//                for (role in state.value.roles.keys) {
//                    if (role.slug == event.slug) {
//                        val roles = state.value.roles
//                        _state.value = state.value.copy(
//                            roles =
//                        )
//                        break
//                    }
//                }
        }

        fun getRoleSelectionLimit(currentValue: Int) {
            var rolesCount = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                state.value.roles.values.stream().forEach { rolesCount += it }
            }

        }
    }
}