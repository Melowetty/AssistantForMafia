package com.sadteam.assistantformafia.ui.gamecreation

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.data.models.RoleType
import com.sadteam.assistantformafia.data.models.entities.DbRole
import com.sadteam.assistantformafia.data.models.entities.DbRole.Companion.toRole
import com.sadteam.assistantformafia.data.repository.RoleRepository
import com.sadteam.assistantformafia.utils.MIN_PLAYERS_COUNT
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Integer.min
import javax.inject.Inject

@HiltViewModel
class GameCreationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val roleRepository: RoleRepository
    ): ViewModel() {
    var state = mutableStateOf(GameCreationState())
        private set
    private val observer = Observer<List<DbRole>> { roles ->
        val rolesFromDb: MutableList<Role> = mutableListOf()
        var distributedPlayers: Int = 0
        for (role in roles) {
            rolesFromDb.add(role.toRole(context))
            distributedPlayers += role.min
        }
        state.value = state.value.copy(
            roles = rolesFromDb,
            distributedPlayers = distributedPlayers
        )
    }

    init {
        viewModelScope.launch(Dispatchers.Main) {
            viewModelScope.launch(Dispatchers.IO) {
                val players = mutableStateListOf<Player>()
                for (i in 1..MIN_PLAYERS_COUNT) {
                    players.add(Player())
                }
                state.value = state.value.copy(
                    players = players
                )
            }
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
                    decreaseRoleCount(event.role)
                }

                is GameCreationEvent.IncrementRole -> {
                    increaseRoleCount(event.role)
                }

                is GameCreationEvent.SetPlayerImage ->
                    setPlayerImage(event.pos, event.image)
            }
        }
    }

    private fun decreaseRoleCount(role: Role) {
        if (role.min < role.selectedCount.value) {
            role.selectedCount.value -= 1
            role.canBeSelectedMore.value = true
            val enemiesCount = getEnemiesCount()
            val availableEnemies = getAvailableEnemiesCount()
            if(state.value.canStart) {
                for (anotherRole in state.value.roles) {
                    if(role.roleType == RoleType.ENEMY) {
                        if(anotherRole.selectedCount.value != anotherRole.max) {
                            anotherRole.canBeSelectedMore.value = true
                        }
                    }
                    else {
                        if(anotherRole.roleType != RoleType.ENEMY || enemiesCount < availableEnemies) {
                            if(anotherRole.selectedCount.value != anotherRole.max) {
                                anotherRole.canBeSelectedMore.value = true
                            }
                        }
                    }
                }
            }
            else {
                if(role.roleType == RoleType.ENEMY) {
                    for (anotherRole in state.value.roles) {
                        if(anotherRole.selectedCount.value != anotherRole.max) {
                            anotherRole.canBeSelectedMore.value = true
                        }
                    }
                }
            }
            state.value = state.value.copy(
                distributedPlayers = state.value.distributedPlayers - 1,
                canStart = false,
                rolesIsDistributed = false,
            )
        }
    }

    private fun increaseRoleCount(role: Role) {
        if (min(state.value.players.size
                    - state.value.distributedPlayers
                    + role.selectedCount.value, role.max) > role.selectedCount.value) {
            role.selectedCount.value += 1
            if (getEnemiesCount() == getAvailableEnemiesCount()) {
                for (enemy in state.value.roles) {
                    if(enemy.roleType == RoleType.ENEMY) {
                        enemy.canBeSelectedMore.value = false
                    }
                }
            }
            val canStart = state.value.distributedPlayers + 1 == state.value.players.size
            if (canStart) {
                for (anotherRole in state.value.roles) {
                    anotherRole.canBeSelectedMore.value = false
                }
            }
            if(role.selectedCount.value == role.max) {
                role.canBeSelectedMore.value = false
            }
            state.value = state.value.copy(
                distributedPlayers = state.value.distributedPlayers + 1,
                canStart = canStart,
                rolesIsDistributed = canStart,
            )
        }
    }

    private fun increasePlayersCount() {
        val players = state.value.players
        players.add(Player())
        state.value = state.value.copy(
            players = players,
            canStart = false,
        )
        recalculateMaxRolesCount()
    }

    private fun deletePlayer(pos: Int) {
        if (state.value.players.size <= MIN_PLAYERS_COUNT) return
        val players = state.value.players
        players.removeAt(pos)
        clearRolesCount()
        state.value = state.value.copy(
            players = players
        )
    }

    private fun setPlayerName(pos: Int, newName: String) {
        val players = state.value.players
        players[pos].name.value = newName
        state.value = state.value.copy(
            players = players
        )
    }

    private fun setPlayerImage(pos: Int, image: ImageBitmap?) {
        val players = state.value.players
        players[pos].icon.value = image
        state.value = state.value.copy(
            players = players
        )
    }

    private fun decreasePlayersCount() {
        if (state.value.players.size <= MIN_PLAYERS_COUNT) return
        val players = state.value.players
        players.removeLast()
        clearRolesCount()
        state.value = state.value.copy(
            players = players
        )
    }

    private fun clearRolesCount() {
        val roles = mutableListOf<Role>()
        var distributedPlayers: Int = 0
        for (role in state.value.roles) {
            role.selectedCount.value = role.min
            role.canBeSelectedMore.value = true
            roles.add(role)
            distributedPlayers += role.min
        }
        state.value = state.value.copy(
            roles = roles,
            distributedPlayers = distributedPlayers,
            canStart = false,
            rolesIsDistributed = false,
        )
    }

    private fun getEnemiesCount(): Int {
        var countEnemies = 0
        for (enemy in state.value.roles) {
            if(enemy.roleType == RoleType.ENEMY) {
                countEnemies += enemy.selectedCount.value
            }
        }
        return countEnemies
    }

    private fun getAvailableEnemiesCount(): Int {
        val availableCountEnemies = (state.value.players.size / 2) - 1 + (state.value.players.size % 2)
        return availableCountEnemies
    }

    private fun recalculateMaxRolesCount() {
        val enemiesCount = getEnemiesCount()
        val availableEnemiesCount = getAvailableEnemiesCount()
        for (role in state.value.roles) {
            if(role.selectedCount.value != role.max) {
                if (role.roleType == RoleType.ENEMY) {
                    if(role.selectedCount.value != role.max) {
                        if(enemiesCount < availableEnemiesCount) {
                            role.canBeSelectedMore.value = true
                        }
                    }
                }
                else {
                    role.canBeSelectedMore.value = true
                }
            }
        }
    }
}