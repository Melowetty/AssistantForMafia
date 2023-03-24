package com.sadteam.assistantformafia.ui.appsettings

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.ExtendedMenuButton
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.components.SmallButton
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.DarkBackground

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
                        icon = painterResource(id = R.drawable.baseline_language_24),
                        title = stringResource(id = R.string.language),
                        onClick = {
                            viewModel.onEvent(AppSettingsViewModel.UIEvent.LanguageChange(
                                context,
                                "en"
                            ))
                        }
                    )
                    Column(
                        modifier = Modifier
                        .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
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
                        ExtendedMenuButton(
                            icon = painterResource(id = R.drawable.baseline_music_note_24),
                            title = stringResource(id = R.string.music),
                        )
                    }
                }
            }
        }
    }
}