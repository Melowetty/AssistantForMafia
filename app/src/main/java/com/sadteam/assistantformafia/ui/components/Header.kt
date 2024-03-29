package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.OtherBackground
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily

/**
 * Шапка с вопросами-ответами и настройками
 * @param modifier модификатор элемента
 * @param navController навигационный контроллер, для обработки кнопки назад
 * @param title заголовок шапки
 * @param isVisibleSettingsButton видимость кнопки настроек
 */
@Composable
fun Header(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    isVisibleSettingsButton: Boolean = true
) {
    val focusManager = LocalFocusManager.current
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
            color = Color.White
        )
        if(isVisibleSettingsButton) {
            IconButton(
                painter = painterResource(id = R.drawable.ic_baseline_settings_24),
                backgroundColor = OtherBackground,
                iconColor = Color.White,
                size = 40.dp,
                description = stringResource(id = R.string.app_settings),
                onClick = {
                    focusManager.clearFocus()
                    navController.navigate(route = Screen.AppSettings.route)
                }
            )
        }
        else {
            Spacer(modifier = Modifier
                .width(40.dp)
                .height(40.dp))
        }
    }
}