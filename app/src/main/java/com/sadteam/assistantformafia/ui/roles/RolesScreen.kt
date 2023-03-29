package com.sadteam.assistantformafia.ui.roles

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.components.SelectCount
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme
import com.sadteam.assistantformafia.utils.IconUtils.Companion.toRoleIcon

@Composable
fun RolesScreen(
    navController: NavController,
    viewModel: GameCreationViewModel,
){
    val state = viewModel.state.value
    AssistantForMafiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Header(
                    title = stringResource(id = R.string.roles),
                    navController = navController
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
                        for (pair in state.roles) {
                            var roleIcon = pair.key.icon.toRoleIcon()
                            if (roleIcon is ImageBitmap) SelectCount(
                            title = pair.key.name,
                            icon = roleIcon,
                            value = pair.value,
                            min = pair.key.min,
                            max = viewModel.getRoleCountLimit(pair.key, pair.value),
                            onDecreasing = {
                                viewModel.onEvent(
                                    GameCreationViewModel.UIEvent.DecrementRole(pair.key)
                                )
                            },
                            onIncreasing = {
                                viewModel.onEvent(
                                    GameCreationViewModel.UIEvent.IncrementRole(pair.key)
                                )
                            }
                        ) else SelectCount(
                            title = pair.key.name,
                            icon = roleIcon as Painter,
                            value = pair.value,
                            min = pair.key.min,
                            max = viewModel.getRoleCountLimit(pair.key, pair.value),
                            onDecreasing = {
                                viewModel.onEvent(
                                    GameCreationViewModel.UIEvent.DecrementRole(pair.key)
                                )
                            },
                            onIncreasing = {
                                viewModel.onEvent(
                                    GameCreationViewModel.UIEvent.IncrementRole(pair.key)
                                )
                            }
                        )
                        }
                    }
                }
            }
        }
    }
}
