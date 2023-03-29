package com.sadteam.assistantformafia.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sadteam.assistantformafia.ui.appsettings.AppSettingsViewModel
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.navigation.SetupNavGraph
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameCreationViewModel:
                GameCreationViewModel = GameCreationViewModel(this)
        val appSettingsViewModel:
                AppSettingsViewModel = AppSettingsViewModel(getPreferences(Context.MODE_PRIVATE))
        appSettingsViewModel.onEvent(AppSettingsViewModel.UIEvent.SetSavedLanguage(this))
        setContent {
            AssistantForMafiaTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController,
                    gameCreationViewModel = gameCreationViewModel,
                    appSettingsViewModel = appSettingsViewModel
                )
            }
        }
    }
}
