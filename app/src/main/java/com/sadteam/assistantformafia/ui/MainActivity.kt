package com.sadteam.assistantformafia.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sadteam.assistantformafia.ui.appsettings.AppSettingsEvent
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
        val context = this
        val gameCreationViewModel:
                GameCreationViewModel by viewModels()
        val appSettingsViewModel:
                AppSettingsViewModel by viewModels()
        setContent {
            LaunchedEffect(key1 = true, block = {
                appSettingsViewModel.onEvent(AppSettingsEvent.SetSavedLanguage(context))
            })
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
