package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.Card
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.components.PlayerNameKeyboard
import com.sadteam.assistantformafia.ui.components.SelectCount
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.DarkBlue

/**
 * Экран создания игры
 */
@Composable
fun GameCreationScreen(
    navController: NavController,
    viewModel: GameCreationViewModel
) {
    val state = viewModel.state.value
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Header(
                title = stringResource(id = R.string.game_creation),
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
                    SelectCount(
                        title = stringResource(id = R.string.players_count),
                        icon = painterResource(id = R.drawable.baseline_people_alt_24),
                        onIncreasing = { viewModel.onEvent(GameCreationViewModel.UIEvent.IncrementPlayers) },
                        onDecreasing = { viewModel.onEvent(GameCreationViewModel.UIEvent.DecrementPlayers) },
                        value = state.players,
                        min = 6,
                        content = {
                            Column(modifier = Modifier
                                .height(350.dp)
                                .verticalScroll(rememberScrollState())
                                .padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                for (number in 1..state.players) {
                                    val mes = remember {
                                        mutableStateOf("")
                                    }
                                    Card(
                                        content = {
                                            PlayerNameKeyboard(
                                                modifier = Modifier.width(160.dp),
                                                value = mes.value,
                                                onValueChange = { newText ->
                                                    mes.value = newText
                                                },
                                                placeholder = "${stringResource(id = R.string.player)} $number",
                                            )
                                        },
                                        mainIcon = painterResource(id = R.drawable.add_a_photo),
                                        secondIcon = painterResource(id = R.drawable.delete)
                                    )
                                }
                            }
                        }
                    )
                    MenuButton(
                        icon = painterResource(id = R.drawable.ic_baseline_assignment_ind_24),
                        title = stringResource(id = R.string.roles),
                        onClick = {
                            navController.navigate(route = Screen.Roles.route)
                        }
                    )
                    MenuButton(
                        icon = painterResource(id = R.drawable.baseline_tune_24),
                        title = stringResource(id = R.string.game_settings),
                    )
                    MenuButton(
                        icon = painterResource(id = R.drawable.baseline_help_24),
                        title = stringResource(id = R.string.game_rules),
                    )
                }
                BigButton(
                    title = stringResource(id = R.string.start),
                    backgroundColor = DarkBlue)
            }
        }
    }
}


