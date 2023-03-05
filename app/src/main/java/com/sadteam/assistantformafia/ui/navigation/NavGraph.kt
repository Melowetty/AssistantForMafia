package com.sadteam.assistantformafia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationScreen
import com.sadteam.assistantformafia.ui.roles.RolesScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.GameCreation.route
    ) {
        composable(
            route = Screen.GameCreation.route
        ) {
            GameCreationScreen(navController = navController)
        }
        composable(
            route = Screen.Roles.route,
            arguments = listOf(
                navArgument(ROLES_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )


        ) {
            RolesScreen(navController = navController, players = it.arguments?.getInt(
                ROLES_ARGUMENT_KEY))
        }
    }
}