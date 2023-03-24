package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily

/**
 * Шапка с вопросами-ответами и настройками
 * @param modifier модификатор элемента
 */
@Composable
fun Header(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    isVisibleSettingsButton: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(navController = navController)
        Text(
            text = title,
            fontFamily = primaryFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        if(isVisibleSettingsButton) {
            IconButton(
                painter = painterResource(id = R.drawable.ic_baseline_settings_24),
                backgroundColor = MaterialTheme.colors.secondary,
                description = stringResource(id = R.string.app_settings),
                onClick = {
                    navController.navigate(route = Screen.AppSettings.route)
                }
            )
        }
        else {
            Spacer(modifier = Modifier
                .width(24.dp)
                .height(24.dp))
        }
    }
}