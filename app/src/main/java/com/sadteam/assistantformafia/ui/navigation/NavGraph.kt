package com.sadteam.assistantformafia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sadteam.assistantformafia.ui.appsettings.AppSettingsScreen
import com.sadteam.assistantformafia.ui.appsettings.AppSettingsViewModel
import com.sadteam.assistantformafia.ui.game.GameViewModel
import com.sadteam.assistantformafia.ui.game.introduction.IntroductionScreen
import com.sadteam.assistantformafia.ui.game.night.NightScreen
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationScreen
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.gamecreation.players.PlayersScreen
import com.sadteam.assistantformafia.ui.gamecreation.roles.RolesScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    gameCreationViewModel: GameCreationViewModel,
    gameViewModel: GameViewModel,
    appSettingsViewModel: AppSettingsViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.GameCreation.route
    ) {
        composable(
            route = Screen.GameCreation.route
        ) {
            GameCreationScreen(
                navController = navController,
                state = gameCreationViewModel.state.value,
                onEvent = gameCreationViewModel::onEvent
            )
        }
        composable(
            route = Screen.Players.route
        ) {
            PlayersScreen(
                navController = navController,
                state = gameCreationViewModel.state.value,
                onEvent = gameCreationViewModel::onEvent
            )
        }
        composable(
            route = Screen.Roles.route,
        ) {
            RolesScreen(
                navController = navController,
                state = gameCreationViewModel.state.value,
                onEvent = gameCreationViewModel::onEvent
            )
        }
		composable(
            route = Screen.AppSettings.route,
        ) {
            AppSettingsScreen(
                navController = navController,
                state = appSettingsViewModel.state.value,
                onEvent = appSettingsViewModel::onEvent
            )
        }
        composable(
            route = Screen.Introduction.route,
        ) {
            IntroductionScreen(
                navController = navController,
                initialState = gameCreationViewModel.state.value,
                state = gameViewModel.state.value.distributionOfRoles,
                onEvent = gameViewModel::onEvent
            )
        }
        composable(
            route = Screen.NightStage.route
        ) {
            NightScreen(
                navController = navController,
                state = gameViewModel.state.value.nightSelectState,
                onEvent = gameViewModel::onEvent
            )
        }
    }
}