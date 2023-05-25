package com.sadteam.assistantformafia.ui.game

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.StartSetRoles
import com.sadteam.assistantformafia.data.models.Effect
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.data.models.RoleType
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState
import com.sadteam.assistantformafia.utils.IconUtils.Companion.toImageBitmap
import com.sadteam.assistantformafia.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max

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

                is GameEvent.StartNightVoting -> {
                    if (state.value.isActive.not()) startGame()
                    initNightVoting()
                }

                is GameEvent.SelectNightTarget ->
                    selectNightTarget(event.index)

                is GameEvent.ClearNightTarget ->
                    clearNightTarget()

                is GameEvent.NextNightSelect ->
                    nextNightSelect()

                is GameEvent.StartDayVoting ->
                    startDayVoting()

                is GameEvent.IncreaseVoices ->
                    increaseVoices(event.playerIndex)

                is GameEvent.DecreaseVoices ->
                    decreaseVoices(event.playerIndex)

                is GameEvent.KickPlayer ->
                    kickPlayer()

                is GameEvent.NextRound ->
                    nextRound()

                is GameEvent.EndGame ->
                    endGame()

                is GameEvent.SelectHandshakeTarget ->
                    selectHandshakeTarget(event.playerIndex)

                is GameEvent.ClearHandshakeTarget ->
                    clearHandshakeTarget()

                is GameEvent.StartHandshake ->
                    startHandshake()

                is GameEvent.KickHandshakeTarget ->
                    kickHandshakeTarget()
            }
        }
    }

    private fun initGame(initialState: GameCreationState) {
        val players = mutableStateListOf<Player>()
        for ((index, player) in initialState.players.withIndex()) {
            val copiedPlayer = player.clone() as Player
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
            isActive = false,
            distributionOfRoles = DistributionOfRolesState(
                targetRole = roles.keys.elementAt(0),
                nextRole = if (roles.size > 2) roles.keys.elementAt(1) else null,
                queuePlayers = players,
                maxCount = roles.values.first(),
                indexTargetRole = 0,
                isEnd = roles.size <= 2
            ),
            nightSelectState = NightSelectState(),
            endGameState = EndGameState(),
            dayVotingState = DayVotingState()
        )
    }

    private fun nextSelectRole() {
        val (_, nextRole, _, _, _, indexTargetRole, _) =
            state.value.distributionOfRoles
        val newIndexTargetRole = indexTargetRole + 1
        val newNextRole = if (state.value.rolesCount.size == newIndexTargetRole + 2) null
        else state.value.rolesCount.keys.elementAt(newIndexTargetRole + 1)
        if (newNextRole == null) {
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
        }.toMutableStateList()

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
        val addition: Int
        val players = state.value.players
        val indexInPlayers = Utils.findIndexPlayerByName(players, player.name.value)
        val indexInQueue = Utils.findIndexPlayerByName(state.value.distributionOfRoles.queuePlayers, player.name.value)
        players[indexInPlayers].apply {
            this.role = role
        }
        val playersChecked = state.value.distributionOfRoles.queuePlayers
        if (role == null) {
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
        val role = state.value.rolesCount.keys.last()
        var players = state.value.players.map { player: Player ->
            if (player.role == null) player.copy(role = role)
            else player
        }

        players = players.map { player: Player ->
            if (player.icon.value != null) player
            else player.copy(icon = mutableStateOf(player.role?.playerIcon?.toImageBitmap()))
        }

        players = players.map { player: Player ->
            if (player.role?.canSelectOneself == true) player.copy(canSelectOneself = true)
            else player
        }.toMutableStateList()
        state.value = state.value.copy(
            isActive = true,
            players = players,
            distributionOfRoles = DistributionOfRolesState()
        )
    }

    private fun initNightVoting() {
        val roles = state.value.rolesCount.filter { it.key.effect != null }
        val targetRole = roles.keys.elementAt(0)
        var isEnd = false
        val nextRole = if (roles.size == 1) null else roles.keys.elementAt(1)
        if (nextRole == null) isEnd = true
        val players = state.value.players
        val previousTarget = players.first { player: Player -> player.role == targetRole }.previousTarget
        var queuePlayers = players.filter { (it.canSelectOneself && it.isLive) || (it.role != targetRole && it.isLive) }
        queuePlayers = queuePlayers.filter { !(targetRole.canSelectSameTarget == false && it == previousTarget) }.toMutableStateList()
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

    private fun clearNightTarget() {
        state.value = state.value.copy(
            nightSelectState = state.value.nightSelectState.copy(
                targetPlayerIndex = -1,
                canNext = false,
            )
        )
    }

    private fun nextNightSelect() {
        val (targetRole, nextRole, oldQueue, targetPlayerIndex, indexTargetRole, _, _) =
            state.value.nightSelectState
        if (targetRole?.effect != null) {
            val playersWithTargetRole = state.value.players.filter { it.role == targetRole && it.isLive }
            state.value.players[state.value.players.indexOf(oldQueue[targetPlayerIndex])].apply {
                if (playersWithTargetRole.size == 1 && playersWithTargetRole.first() == this) canSelectOneself = false
                addEffect(targetRole.effect)
            }
            val player = state.value.players[state.value.players.indexOf(oldQueue[targetPlayerIndex])]
            playersWithTargetRole.map { rolePlayer: Player ->
                rolePlayer.apply {
                    previousTarget = player
                }
            }
        }
        val roles = state.value.rolesCount.filter { it.key.effect != null }
        val newIndexTargetRole = indexTargetRole + 1
        val newNextRole = if (roles.size == newIndexTargetRole + 1) null
        else roles.keys.elementAt(newIndexTargetRole + 1)
        if (newNextRole == null) {
            state.value = state.value.copy(
                nightSelectState = state.value.nightSelectState.copy(
                    isEnd = true
                )
            )
        }
        val players = state.value.players
        val previousTarget = players.first { player: Player -> player.role == nextRole }.previousTarget
        var queuePlayers = players.filter { (it.canSelectOneself && it.isLive) || (it.role != nextRole && it.isLive) }
        queuePlayers = queuePlayers.filter { !(nextRole?.canSelectSameTarget == false && it == previousTarget) }.toMutableStateList()
        state.value = state.value.copy(
            nightSelectState = state.value.nightSelectState.copy(
                targetRole = nextRole,
                targetPlayerIndex = -1,
                nextRole = newNextRole,
                queuePlayers = queuePlayers,
                indexTargetRole = newIndexTargetRole,
                canNext = false,
            )
        )
    }

    // todo иногда бывают приколы с двойными походами бабочки и тройными убийствами, нужно пересмотреть логику

    private fun startDayVoting() {
        val (targetRole, _, oldQueue, targetPlayerIndex, _, _, _) =
            state.value.nightSelectState
        val playersWithTargetRole = state.value.players.filter { it.role == targetRole && it.isLive }.toMutableList()
        if (targetRole?.effect != null) {
            state.value.players[state.value.players.indexOf(oldQueue[targetPlayerIndex])].apply {
                if (playersWithTargetRole.size == 1 && playersWithTargetRole.first() == this) canSelectOneself = false
                addEffect(targetRole.effect)
            }
            val player = state.value.players[state.value.players.indexOf(oldQueue[targetPlayerIndex])]
            playersWithTargetRole.map { rolePlayer: Player ->
                rolePlayer.apply {
                    previousTarget = player
                }
            }
        }
        for (player in state.value.players) {
            if (player.effects.contains(Effect.KILL)
                && player.effects.contains(Effect.HEAL).not()
            ) {
                if (player.effects.contains(Effect.LOVE)) {
                    if (player.effects.contains(Effect.KILL)) {
                        for (harlot in state.value.players) {
                            if (harlot.role?.effect == Effect.LOVE
                                && harlot.effects.contains(Effect.HEAL).not()
                            ) {
                                harlot.isLive = false
                                harlot.addEffect(Effect.KILL)
                                break
                            }
                        }
                    }
                }
                player.isLive = false
            } else if (player.effects.contains(Effect.LOVE)) {
                player.canVote = false
            }
            player.effects.sortBy { effect: Effect -> effect.priority }
        }
        val players = state.value.players.toMutableStateList()
        players.sortBy { player: Player -> player.isLive.not() }
        state.value = state.value.copy(
            dayVotingState = state.value.dayVotingState.copy(
                players = players,
                countLivePlayers = players.filter { player: Player -> player.isLive }.size,
                countPlayersWhoCanVote = players.filter { player: Player ->
                    player.isLive && player.effects.contains(
                        Effect.LOVE
                    ).not()
                }.size,
            )
        )
        checkGameIsEnd()
        checkHandshake()
    }

    private fun increaseVoices(playerIndex: Int) {
        state.value.dayVotingState.players[playerIndex].apply {
            if (voices.value < state.value.dayVotingState.countPlayersWhoCanVote) {
                voices.value += 1
                val countPlayersWhoCanVote = state.value.dayVotingState.countPlayersWhoCanVote
                val totalVoices = state.value.dayVotingState.totalVoices
                if (voices.value == totalVoices + 1 && voices.value == countPlayersWhoCanVote - 1) {
                    canBeVotedMore = false
                }
                if (totalVoices + 1 == countPlayersWhoCanVote) {
                    for (player in state.value.dayVotingState.players) {
                        player.apply {
                            canBeVotedMore = false
                        }
                    }
                }
                state.value = state.value.copy(
                    dayVotingState = state.value.dayVotingState.copy(
                        totalVoices = state.value.dayVotingState.totalVoices + 1,
                        canKick = checkCanKick(),
                    )
                )
            }
        }
    }

    private fun decreaseVoices(playerIndex: Int) {
        state.value.dayVotingState.players[playerIndex].apply {
            if (voices.value > 0) {
                val countPlayersWhoCanVote = state.value.dayVotingState.countPlayersWhoCanVote
                for(player in state.value.dayVotingState.players) {
                    player.apply {
                        canBeVotedMore = voices.value != countPlayersWhoCanVote - 1
                    }
                }
                voices.value -= 1
                state.value = state.value.copy(
                    dayVotingState = state.value.dayVotingState.copy(
                        totalVoices = state.value.dayVotingState.totalVoices - 1,
                        canKick = checkCanKick(),
                    )
                )
            }
        }
    }

    private fun kickPlayer() {
        if (!checkCanKick()) return
        val players = state.value.players
        var maxVoices = Int.MIN_VALUE
        var indexPlayerWithMaxVoices = 0
        for ((index, player) in players.withIndex()) {
            if (maxVoices < player.voices.value) {
                maxVoices = player.voices.value
                indexPlayerWithMaxVoices = index
            }
        }
        state.value.players[indexPlayerWithMaxVoices].apply {
            clearEffects()
            addEffect(Effect.KICK)
            isLive = false
        }
        state.value = state.value.copy(
            dayVotingState = state.value.dayVotingState.copy(
                isEnd = true,
            )
        )
        checkGameIsEnd()
        checkHandshake()
    }

    private fun checkCanKick(): Boolean {
        val players = state.value.players
        var maxVoices = Int.MIN_VALUE
        for (player in players) {
            maxVoices = max(player.voices.value, maxVoices)
        }
        return players.filter { player: Player -> player.voices.value == maxVoices }.size == 1
    }

    private fun nextRound() {
        val newRolesCount: MutableMap<Role, Int> = mutableMapOf()
        for (player in state.value.players.filter { player: Player -> player.isLive }) {
            if (newRolesCount.contains(player.role)) {
                newRolesCount[player.role!!] = newRolesCount[player.role]!! + 1
            } else {
                newRolesCount[player.role!!] = 1
            }
        }
        val players = state.value.players
        for (player in players) {
            player.voices.value = 0
            if (player.isLive.not()) {
                player.effects = player.effects.filter { effect: Effect ->
                    effect == Effect.KICK || effect == Effect.KILL
                }
                    .toMutableList()
            } else {
                player.clearEffects()
                player.canVote = true
            }
        }
        state.value = state.value.copy(
            players = players,
            rolesCount = newRolesCount,
            dayVotingState = DayVotingState(),
            nightSelectState = NightSelectState()
        )
    }

    private fun checkGameIsEnd(): Boolean {
        val players = state.value.dayVotingState.players.filter { player: Player -> player.isLive }
        val countEnemies = mutableMapOf<Role, Int>()
        var countCommons = 0
        for (player in players) {
            if (player.role?.roleType == RoleType.ENEMY) {
                if (player.role != null) {
                    if (countEnemies.containsKey(player.role)) {
                        countEnemies[player.role!!] = countEnemies[player.role!!]!! + 1
                    } else {
                        countEnemies[player.role!!] = 1
                    }
                }
            } else {
                countCommons += 1
            }
        }
        val sumEnemies = countEnemies.values.sum()
        if (sumEnemies >= countCommons && sumEnemies + countCommons != 3) {
            state.value = state.value.copy(
                dayVotingState = state.value.dayVotingState.copy(
                    gameIsEnd = true,
                ),
                endGameState = state.value.endGameState.copy(
                    roleWin = getMaxCountOfRoles(countEnemies),
                )
            )
            return true
        }
        if (sumEnemies == 0) {
            state.value = state.value.copy(
                dayVotingState = state.value.dayVotingState.copy(
                    gameIsEnd = true,
                ),
                endGameState = state.value.endGameState.copy(
                    roleWin = StartSetRoles().getRoles()[5],
                )
            )
            return true
        }
        return false
    }

    private fun getMaxCountOfRoles(roles: Map<Role, Int>): Role? {
        var maxRole: Role? = null
        var maxCount = 0
        for ((role, count) in roles) {
            if (count > maxCount) {
                maxRole = role
                maxCount = count
            }
        }
        return maxRole
    }

    private fun endGame() {
        state.value = GameState(
            endGameState = state.value.endGameState.copy()
        )
    }

    private fun selectHandshakeTarget(index: Int) {
        state.value = state.value.copy(
            handshakeState = state.value.handshakeState.copy(
                targetPlayerIndex = index,
                canKick = true,
            )
        )
    }

    private fun clearHandshakeTarget() {
        state.value = state.value.copy(
            handshakeState = state.value.handshakeState.copy(
                targetPlayerIndex = -1,
                canKick = false,
            )
        )
    }

    private fun startHandshake() {
        state.value = state.value.copy(
            handshakeState = state.value.handshakeState.copy(
                players = state.value.dayVotingState.players.filter { it.isLive }.toMutableStateList()
            )
        )
    }

    private fun kickHandshakeTarget() {
        state.value.dayVotingState.players[state.value.dayVotingState.players.indexOf(state.value.handshakeState.players[state.value.handshakeState.targetPlayerIndex])].apply {
            voices.value = Int.MAX_VALUE
            state.value = state.value.copy(
                dayVotingState = state.value.dayVotingState.copy(
                    totalVoices = Int.MAX_VALUE,
                    canKick = true
                )
            )
        }
        kickPlayer()
        state.value = state.value.copy(
            handshakeState = state.value.handshakeState.copy(
                gameIsEnd = true
            )
        )
    }

    private fun checkHandshake() {
        if(state.value.dayVotingState.gameIsEnd.not() && state.value.dayVotingState.players.filter { it.isLive }.size == 3) {
            state.value = state.value.copy(
                dayVotingState = state.value.dayVotingState.copy(
                    isHandshake = true,
                )
            )
        }
    }
}