package com.sadteam.assistantformafia.ui.game.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.VotingPlayerCard
import com.sadteam.assistantformafia.ui.game.DayVotingState
import com.sadteam.assistantformafia.ui.game.GameEvent
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.*
import com.sadteam.assistantformafia.utils.Utils

@Composable
fun DayScreen(
    navController: NavController,
    state: DayVotingState,
    onEvent: (GameEvent) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(
            GameEvent.StartDayVoting
        )
    }
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.stage) + " " + stringResource(id = R.string.day),
        backgroundColor = DayStageBackground,
        backgroundContent = {
            Row(modifier = Modifier
                .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.city),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // todo необходимо чтобы солнце не сдвигала элементы верстки
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
                    .zIndex(0f),
                painter = painterResource(id = R.drawable.sun),
                contentDescription = ""
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = if(!state.isEnd) stringResource(id = R.string.voting_time) else stringResource(id = R.string.voting_ended),
                fontSize = 24.sp,
                fontFamily = primaryFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.size(5.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(state.players){index: Int, player: Player ->
                        VotingPlayerCard(
                            backgroundColor = player.role?.getBackgroundColor()?: BaseRoleBackgroundColor,
                            name = player.name.value,
                            role = player.role?.getTranslatedName()?: "None",
                            photo = player.icon.value?: Utils.getBitmapFromImage(LocalContext.current, R.drawable.add_a_photo).asImageBitmap(),
                            effects = player.effects,
                            isEnabled = player.isLive,
                            canBeVoted = state.isEnd.not() && player.isLive && player.canVote,
                            value = player.voices.value,
                            max = state.countPlayersWhoCanVote - state.totalVoices + player.voices.value,
                            onIncrease = {
                                onEvent(
                                    GameEvent.IncreaseVoices(index)
                                )
                            },
                            onDecrease = {
                                onEvent(
                                    GameEvent.DecreaseVoices(index)
                                )
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                if(state.isEnd) {
                    BigButton(
                        title = stringResource(id = R.string.next_round),
                        backgroundColor = SecondaryBackground,
                        disabledBackground = DisabledSecondaryBackground,
                        onClick = {
                            onEvent(
                                GameEvent.NextRound
                            )
                            navController.navigate(Screen.NightStage.route)
                        }
                    )
                }
                else {
                    BigButton(
                        title = stringResource(id = R.string.kick),
                        backgroundColor = SecondaryBackground,
                        isDisabled = !state.canKick,
                        disabledBackground = DisabledSecondaryBackground,
                        onClick = {
                            onEvent(
                                GameEvent.KickPlayer
                            )
                        }
                    )
                }
            }
        }
    }
}