package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.layout.*
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
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.components.SelectCount
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.DarkBackground
import com.sadteam.assistantformafia.ui.theme.DarkBlue

/**
 * Экран создания игры
 */
@Composable
fun GameCreationScreen(
    navController: NavController,
    viewModel: GameCreationViewModel = GameCreationViewModel()
) {
    val state = viewModel.state.value
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkBackground,
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
                        value = state.players,
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
                    backgroundColor = DarkBlue)
            }
        }
    }
}


