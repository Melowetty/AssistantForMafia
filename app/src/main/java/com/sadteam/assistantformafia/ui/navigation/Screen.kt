package com.sadteam.assistantformafia.ui.navigation

sealed class Screen (val route: String){
    object GameCreation: Screen(route = "game_creation_options")
    object Roles: Screen(route = "roles_list")
    object AppSettings: Screen(route = "app_settings")
    object Introduction: Screen(route = "introduction")
    object NightStage: Screen(route = "game/stage_night")
}