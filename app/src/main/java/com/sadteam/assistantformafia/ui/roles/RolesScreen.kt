package com.sadteam.assistantformafia.ui.roles

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationViewModel
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme
import com.sadteam.assistantformafia.ui.theme.DarkBackground

@Composable
fun RolesScreen(
    navController: NavController,
    viewModel: GameCreationViewModel = GameCreationViewModel(),
){
    val state = viewModel.state.value
    var mafias by remember {
        mutableStateOf(1)
    }
    var innocents by remember {
        mutableStateOf(1)
    }
    var commmissars by remember {
        mutableStateOf(0)
    }
    var harlots by remember {
        mutableStateOf(0)
    }
    var doctors by remember {
        mutableStateOf(0)
    }
    var maniacs by remember {
        mutableStateOf(0)
    }
    val rolesCount = mafias + commmissars + harlots + doctors + maniacs
    val availableCount = state.players.minus(rolesCount)
    AssistantForMafiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DarkBackground
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
//                        SelectCount(
//                            title = stringResource(id = R.string.role_mafia),
//                            icon = painterResource(id = R.drawable.baseline_help_24),
//                            minCount = 1,
//                            maxCount = availableCount + mafias,
//                            onValueChange = {
//                                mafias = it
//                            }
//                        )
//                        SelectCount(
//                            title = stringResource(id = R.string.role_innocent),
//                            icon = painterResource(id = R.drawable.baseline_help_24),
//                            minCount = 1,
//                            maxCount = availableCount + innocents,
//                            onValueChange = {
//                                innocents = it
//                            }
//                        )
//                        SelectCount(
//                            title = stringResource(id = R.string.role_commissar),
//                            icon = painterResource(id = R.drawable.baseline_help_24),
//                            minCount = 0,
//                            maxCount = availableCount + commmissars,
//                            onValueChange = {
//                                commmissars = it
//                            }
//                        )
//                        SelectCount(
//                            title = stringResource(id = R.string.role_harlot),
//                            icon = painterResource(id = R.drawable.baseline_help_24),
//                            minCount = 0,
//                            maxCount = availableCount + harlots,
//                            onValueChange = {
//                                harlots = it
//                            }
//                        )
//                        SelectCount(
//                            title = stringResource(id = R.string.role_doctor),
//                            icon = painterResource(id = R.drawable.baseline_help_24),
//                            minCount = 0,
//                            maxCount = availableCount + doctors,
//                            onValueChange = {
//                                doctors = it
//                            }
//                        )
//                        SelectCount(
//                            title = stringResource(id = R.string.role_maniac),
//                            icon = painterResource(id = R.drawable.baseline_help_24),
//                            minCount = 0,
//                            maxCount = availableCount + maniacs,
//                            onValueChange = {
//                                maniacs = it
//                            }
//                        )
                    }
                }
            }
        }
    }
}
