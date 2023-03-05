package com.sadteam.assistantformafia.ui.navigation

const val ROLES_ARGUMENT_KEY = "playersValue"
sealed class Screen (val route: String){
    object GameCreation: Screen(route = "game_creation_options")
    object Roles: Screen(route = "roles_list?playersValue={playersValue}") {
        fun passPlayersValue(playersValue: Int = 1): String {
            return "roles_list?playersValue=$playersValue"
        }
    }
}