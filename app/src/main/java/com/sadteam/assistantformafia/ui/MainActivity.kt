package com.sadteam.assistantformafia.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sadteam.assistantformafia.MafiaApplication
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.navigation.SetupNavGraph
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    private val gameCreationViewModel:
            GameCreationViewModel = GameCreationViewModel(MafiaApplication.instance)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistantForMafiaTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController,
                    gameCreationViewModel = gameCreationViewModel)
            }
        }
    }
}
