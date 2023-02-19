package com.sadteam.assistantformafia.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationScreen
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistantForMafiaTheme {
                GameCreationScreen()
            }
        }
    }
}
