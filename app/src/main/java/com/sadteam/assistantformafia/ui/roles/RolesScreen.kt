package com.sadteam.assistantformafia.ui.roles

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.components.SelectCount
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme

@Composable
fun RolesScreen(
    navController: NavController,
    viewModel: GameCreationViewModel,
){
    val state = viewModel.state.value
    AssistantForMafiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
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
                            SelectCount(
                                title = pair.key.name,
                                icon = painterResource(id = R.drawable.baseline_help_24),
                                value = pair.value,
                                onDecreasing = {
                                    viewModel.onEvent(
                                        GameCreationViewModel.UIEvent.DecrementRole(pair.key))
                                },
                                onIncreasing = {
                                    viewModel.onEvent(
                                        GameCreationViewModel.UIEvent.IncrementRole(pair.key))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
