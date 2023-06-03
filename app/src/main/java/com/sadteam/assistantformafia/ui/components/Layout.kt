package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme
import com.sadteam.assistantformafia.ui.theme.MainBackground

@Composable
fun MainLayout(
    navController: NavController,
    title: String,
    isVisibleSettingsButton: Boolean = true,
    backgroundColor: Color = MainBackground,
    backgroundContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    AssistantForMafiaTheme {
        Surface(
            color = backgroundColor,
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                backgroundContent()
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Header(
                        title = title,
                        navController = navController,
                        isVisibleSettingsButton = isVisibleSettingsButton,
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 15.dp,
                                end = 10.dp,
                                bottom = 30.dp,
                                start = 10.dp
                            ),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        content()
                    }
                }
            }
        }
    }
}