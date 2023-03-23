package com.sadteam.assistantformafia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationScreen
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.roles.RolesScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    gameCreationViewModel: GameCreationViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.GameCreation.route
    ) {
        composable(
            route = Screen.GameCreation.route
        ) {
            GameCreationScreen(navController = navController, gameCreationViewModel)
        }
        composable(
            route = Screen.Roles.route,
        ) {
            RolesScreen(navController = navController, gameCreationViewModel)
        }
    }
}