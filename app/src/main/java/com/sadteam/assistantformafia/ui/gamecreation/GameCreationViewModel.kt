package com.sadteam.assistantformafia.ui.gamecreation

import android.os.Build
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sadteam.assistantformafia.models.Role

data class GameCreationState(
    val players: Int = 4,
    val roles: Map<Role, Int> = mapOf(),
)

class GameCreationViewModel: ViewModel() {
    private val _state = mutableStateOf(GameCreationState())
    val state: State<GameCreationState> = _state

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