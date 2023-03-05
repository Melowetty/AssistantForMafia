package com.sadteam.assistantformafia.ui.navigation

sealed class Screen (val route: String){
    object GameCreation: Screen(route = "game_creation_options")
    object Roles: Screen(route = "roles_list?playerValue={playerValue]")


}