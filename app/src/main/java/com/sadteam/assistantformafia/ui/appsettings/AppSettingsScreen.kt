package com.sadteam.assistantformafia.ui.appsettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.ExtendedMenuButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.bounceClick
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.SettingsBackground

@Composable
fun AppSettingsScreen(
    navController: NavController,
    state: AppSettingsState,
    onEvent: (AppSettingsEvent) -> Unit,
) {
    val context = LocalContext.current
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.app_settings),
        isVisibleSettingsButton = false,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SelectLanguage(
                title = stringResource(id = R.string.select_language),
                onSetEnglish = { onEvent(AppSettingsEvent.SetEnglish(context)) },
                onSetRussian = { onEvent(AppSettingsEvent.SetRussian(context)) },
                currentLocale = state.language
            )
            ExtendedMenuButton(
                modifier = Modifier
                    .bounceClick()
                    .background(
                        color = SettingsBackground,
                        shape = RoundedCornerShape(10.dp)
                    ),
                title = stringResource(id = R.string.feedback),
                onClick = {
                    navController.navigate(route = Screen.Feedback.route)
                },
                icon = painterResource(id = R.drawable.baseline_feedback_24),
            )
            ExtendedMenuButton(
                modifier = Modifier
                    .bounceClick()
                    .background(
                        color = SettingsBackground,
                        shape = RoundedCornerShape(10.dp)
                    ),
                title = stringResource(id = R.string.about_app),
                onClick = {
                    navController.navigate(route = Screen.AppInfo.route)
                },
                icon = painterResource(id = R.drawable.baseline_info_24),
            )
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(
//                        color = SettingsBackground,
//                        shape = RoundedCornerShape(10.dp)
//                    ),
//                verticalArrangement = Arrangement.spacedBy(0.dp)
//            ) {
//                ExtendedMenuButton(
//                    icon = painterResource(id = R.drawable.baseline_volume_up_24),
//                    title = stringResource(id = R.string.volume),
//                    content = {
//                        Slider(
//                            value = state.soundVolume,
//                            onValueChange = {
//                                onEvent(AppSettingsEvent.SoundVolumeChange(value = it))
//                            }
//                        )
//                    }
//                )
//                Divider(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(3.dp)
//                        .padding(end = 20.dp, start = 20.dp)
//                )
//                ExtendedMenuButton(
//                    icon = painterResource(id = R.drawable.baseline_music_note_24),
//                    title = stringResource(id = R.string.music),
//                    content = {
//                        Slider(
//                            value = state.musicVolume,
//                            onValueChange = {
//                                onEvent(AppSettingsEvent.MusicVolumeChange(value = it))
//                            }
//                        )
//                    }
//                )
//            }
        }
    }
}
