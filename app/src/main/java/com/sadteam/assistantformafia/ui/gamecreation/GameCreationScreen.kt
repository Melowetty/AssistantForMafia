package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.MenuButton
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
            MenuButton(
                title = stringResource(id = R.string.players_count),
                onClick = {
                    navController.navigate(route = Screen.Players.route)
                },
                currentValue = state.players.size.toString(),
                icon = painterResource(id = R.drawable.baseline_people_alt_24),
            )
            MenuButton(
                icon = painterResource(id = R.drawable.ic_baseline_assignment_ind_24),
                title = stringResource(id = R.string.roles),
                onClick = {
                    navController.navigate(route = Screen.Roles.route)
                }
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


