package com.sadteam.assistantformafia.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sadteam.assistantformafia.ui.appsettings.AppSettingsViewModel
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.navigation.SetupNavGraph
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameCreationViewModel:
                GameCreationViewModel by viewModels()
        val appSettingsViewModel:
                AppSettingsViewModel by viewModels()
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
