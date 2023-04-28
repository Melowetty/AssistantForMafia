package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.Card
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.components.PlayerNameKeyboard
import com.sadteam.assistantformafia.ui.components.SelectCount
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.DisabledSecondaryBackground
import com.sadteam.assistantformafia.ui.theme.SecondaryBackground

/**
 * Экран создания игры
 */
@Composable
fun GameCreationScreen(
    navController: NavController,
    state: GameCreationState,
    onEvent: (GameCreationEvent) -> Unit,
) {
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.game_creation),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SelectCount(
                title = stringResource(id = R.string.players_count),
                popupModifier = Modifier.fillMaxHeight(0.75f),
                icon = painterResource(id = R.drawable.baseline_people_alt_24),
                onIncreasing = { onEvent(GameCreationEvent.IncrementPlayers) },
                onDecreasing = { onEvent(GameCreationEvent.DecrementPlayers) },
                value = state.players.size,
                min = 6,
                content = {
                    LazyColumn(
                        modifier = Modifier.weight(0.5f).padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        content = {
                        itemsIndexed(state.players) {index: Int, item: Player ->
                            Card(
                                content = {
                                    PlayerNameKeyboard(
                                        modifier = Modifier.width(160.dp),
                                        value = item.name.value,
                                        onValueChange = { newText ->
                                            onEvent(
                                                GameCreationEvent.SetPlayerName(index, newText)
                                            )
                                        },
                                        placeholder = stringResource(id = R.string.enter_name),
                                    )
                                },
                                mainIcon = painterResource(id = R.drawable.add_a_photo),
                                secondIcon = painterResource(id = R.drawable.delete),
                                onSecondIconClick = {
                                    onEvent(
                                        GameCreationEvent.DeletePlayer(index)
                                    )
                                }
                            )
                        }
                    })
                }
            )
            MenuButton(
                icon = painterResource(id = R.drawable.ic_baseline_assignment_ind_24),
                title = stringResource(id = R.string.roles),
                onClick = {
                    navController.navigate(route = Screen.Roles.route)
                }
            )
            MenuButton(
                icon = painterResource(id = R.drawable.baseline_tune_24),
                title = stringResource(id = R.string.game_settings),
            )
            MenuButton(
                icon = painterResource(id = R.drawable.baseline_help_24),
                title = stringResource(id = R.string.game_rules),
            )
        }
        BigButton(
            title = stringResource(id = R.string.start),
            backgroundColor = SecondaryBackground,
            isDisabled = !state.canStart,
            disabledBackground = DisabledSecondaryBackground,
            onClick = {
                navController.navigate(route = Screen.Introduction.route)
            }
        )
    }
}


