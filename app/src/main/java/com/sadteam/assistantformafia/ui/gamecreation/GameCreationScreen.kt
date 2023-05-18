package com.sadteam.assistantformafia.ui.gamecreation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.ExtendedMenuButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.DisabledSecondaryBackground
import com.sadteam.assistantformafia.ui.theme.SecondaryBackground
import com.sadteam.assistantformafia.ui.theme.SettingsBackground

/**
 * Экран создания игры
 */
@Composable
fun GameCreationScreen(
    navController: NavController,
    state: GameCreationState,
    onEvent: (GameCreationEvent) -> Unit,
) {
    val context = LocalContext.current
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.game_creation),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ExtendedMenuButton(
                modifier = Modifier
                    .background(
                        color = SettingsBackground,
                        shape = RoundedCornerShape(10.dp)
                    ),
                title = stringResource(id = R.string.players_count),
                onClick = {
                    navController.navigate(route = Screen.Players.route)
                },
                currentValue = state.players.size.toString(),
                icon = painterResource(id = R.drawable.baseline_people_alt_24),
            )
            ExtendedMenuButton(
                modifier = Modifier
                    .background(
                        color = SettingsBackground,
                        shape = RoundedCornerShape(10.dp)
                    ),
                icon = painterResource(id = R.drawable.ic_baseline_assignment_ind_24),
                title = stringResource(id = R.string.roles),
                currentValue = if(state.rolesIsDistributed) stringResource(id = R.string.roles_distributed)
                else stringResource(id = R.string.roles_not_distributed),
                onClick = {
                    navController.navigate(route = Screen.Roles.route)
                }
            )
            ExtendedMenuButton(
                modifier = Modifier
                    .background(
                        color = SettingsBackground,
                        shape = RoundedCornerShape(10.dp)
                    ),
                icon = painterResource(id = R.drawable.baseline_help_24),
                title = stringResource(id = R.string.game_rules),
                onClick = {
                    Toast.makeText(context, "Coming soon...", Toast.LENGTH_LONG).show()
                }
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


