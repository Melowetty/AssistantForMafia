package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.Card
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.components.PlayerNameKeyboard
import com.sadteam.assistantformafia.ui.components.SelectCount
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.DarkBlue

/**
 * Экран создания игры
 */
@Composable
fun GameCreationScreen(
    navController: NavController,
    viewModel: GameCreationViewModel
) {
    val state = viewModel.state.value
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Header(
                title = stringResource(id = R.string.game_creation),
                navController = navController
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 30.dp,
                        end = 10.dp,
                        bottom = 30.dp,
                        start = 10.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SelectCount(
                        title = stringResource(id = R.string.players_count),
                        icon = painterResource(id = R.drawable.baseline_people_alt_24),
                        onIncreasing = { viewModel.onEvent(GameCreationViewModel.UIEvent.IncrementPlayers) },
                        onDecreasing = { viewModel.onEvent(GameCreationViewModel.UIEvent.DecrementPlayers) },
                        value = state.players.size,
                        min = 6,
                        content = {
                            Column(modifier = Modifier
                                .height(350.dp)
                                .verticalScroll(rememberScrollState())
                                .padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                for ((i, player) in state.players.withIndex()) {
                                    Card(
                                        content = {
                                            PlayerNameKeyboard(
                                                modifier = Modifier.width(160.dp),
                                                value = player.name,
                                                onValueChange = { newText ->
                                                    viewModel.onEvent(
                                                        GameCreationViewModel.UIEvent.SetPlayerName(i, newText)
                                                    )
                                                },
                                                placeholder = "${stringResource(id = R.string.enter_name)}",
                                            )
                                        },
                                        mainIcon = painterResource(id = R.drawable.add_a_photo),
                                        secondIcon = painterResource(id = R.drawable.delete),
                                        onSecondIconClick = {
                                            viewModel.onEvent(
                                                GameCreationViewModel.UIEvent.DeletePlayer(i)
                                            )
                                        }
                                    )
                                }
                            }
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
                    backgroundColor = DarkBlue,
                    onClick = {
                        navController.navigate(route = Screen.Introduction.route)
                    }
                )
            }
        }
    }
}


