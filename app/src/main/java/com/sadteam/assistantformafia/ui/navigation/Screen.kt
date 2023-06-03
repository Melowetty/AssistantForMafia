package com.sadteam.assistantformafia.ui.navigation

sealed class Screen (val route: String){
    object GameCreation: Screen(route = "game_creation_options")
    object Players: Screen(route = "players")
    object Roles: Screen(route = "roles_list")
    object Faq: Screen(route = "faq")
    object AppSettings: Screen(route = "app_settings")
    object AppInfo: Screen(route = "app_settings/info")
    object Feedback: Screen(route = "app_settings/feedback")
    object Introduction: Screen(route = "introduction")
    object NightStage: Screen(route = "game/stage_night")
    object DayStage: Screen(route = "game/stage_day")
    object EndStage: Screen(route = "game/stage_end")
    object HandshakeStage: Screen(route = "game/stage_handshake")
}