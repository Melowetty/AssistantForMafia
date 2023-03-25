package com.sadteam.assistantformafia.ui.appsettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.ExtendedMenuButton
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.theme.SettingsBackground

@Composable
fun AppSettingsScreen(
    navController: NavController,
    viewModel: AppSettingsViewModel = AppSettingsViewModel()
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(
                navController = navController,
                title = stringResource(id = R.string.app_settings),
                isVisibleSettingsButton = false,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 30.dp,
                        end = 10.dp,
                        bottom = 30.dp,
                        start = 10.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ExtendedMenuButton(
                        modifier = Modifier
                            .background(
                                color = SettingsBackground,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        icon = painterResource(id = R.drawable.baseline_language_24),
                        title = stringResource(id = R.string.language),
                        onClick = {
                            viewModel.onEvent(AppSettingsViewModel.UIEvent.LanguageChange(
                                context,
                                "ru_RU"
                            ))
                        },
                        currentValue = viewModel.state.value.language.displayLanguage,
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = SettingsBackground,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        ExtendedMenuButton(
                            icon = painterResource(id = R.drawable.baseline_volume_up_24),
                            title = stringResource(id = R.string.volume),
                            content = {
                                Slider(
                                    value = viewModel.state.value.soundVolume,
                                    onValueChange = {
                                        viewModel.onEvent(AppSettingsViewModel.UIEvent.SoundVolumeChange(value = it))
                                    }
                                )
                            }
                        )
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(3.dp)
                                .padding(end = 20.dp, start = 20.dp)
                        )
                        ExtendedMenuButton(
                            icon = painterResource(id = R.drawable.baseline_music_note_24),
                            title = stringResource(id = R.string.music),
                            content = {
                                Slider(
                                    value = viewModel.state.value.musicVolume,
                                    onValueChange = {
                                        viewModel.onEvent(AppSettingsViewModel.UIEvent.MusicVolumeChange(value = it))
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}