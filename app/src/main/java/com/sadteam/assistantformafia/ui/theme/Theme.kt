package com.sadteam.assistantformafia.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val colorScheme = darkColors(
    primary = Color.White,
    background = DarkGreen,
    secondary = DarkBlue,
)

@Composable
fun AssistantForMafiaTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}