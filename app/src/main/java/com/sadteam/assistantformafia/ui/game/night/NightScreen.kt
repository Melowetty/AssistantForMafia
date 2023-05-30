package com.sadteam.assistantformafia.ui.game.night

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.SelectRoleCard
import com.sadteam.assistantformafia.ui.game.GameEvent
import com.sadteam.assistantformafia.ui.game.NightSelectState
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.*
import com.sadteam.assistantformafia.utils.Utils

@Composable
fun NightScreen(
    navController: NavController,
    state: NightSelectState,
    onEvent: (GameEvent) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        onEvent(
            GameEvent.StartNightVoting
        )
    })
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.stage) + " " + stringResource(id = R.string.night),
        backgroundColor = NightStageBackground,
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
            Image(
                modifier = Modifier
                    .align(CenterHorizontally),
                painter = painterResource(id = R.drawable.moon),
                contentDescription = ""
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 10.sp)) {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontFamily = primaryFontFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        ) {
                            withStyle(
                                style = SpanStyle(
                                    color = state.targetRole?.getTextColor() ?: Color.White,
                                ),
                            ) {
                                append(state.targetRole?.getTranslatedName() ?: "None")
                            }
                            append(" ")
                            append(context.getString(R.string.target))
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.size(5.dp))
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(state.queuePlayers){index: Int, item: Player ->
                        SelectRoleCard(
                            backgroundColor = item.role?.getBackgroundColor()?: BaseRoleBackgroundColor,
                            text = item.name.value,
                            description = item.role?.getTranslatedName() ?: "",
                            mainIcon = item.icon.value?: Utils.getBitmapFromImage(LocalContext.current, R.drawable.add_a_photo).asImageBitmap(),
                            mainIconModifier = if(item.icon.value != null) Modifier else Modifier.padding(8.dp),
                            checked = index == state.targetPlayerIndex,
                            onCheckboxClicked = {
                                if (it) onEvent(GameEvent.SelectNightTarget(index))
                                else onEvent(GameEvent.ClearNightTarget)
                            },
                        )
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                if(state.isEnd) {
                    BigButton(
                        title = stringResource(id = R.string.start_voting),
                        backgroundColor = SecondaryBackground,
                        isDisabled = !state.canNext,
                        disabledBackground = DisabledSecondaryBackground,
                        onClick = {
                            navController.navigate(Screen.DayStage.route) {
                                popUpTo(route = Screen.GameCreation.route)
                            }
                        }
                    )
                }
                else {
                    BigButton(
                        title = stringResource(id = R.string.next),
                        backgroundColor = SecondaryBackground,
                        isDisabled = !state.canNext,
                        disabledBackground = DisabledSecondaryBackground,
                        onClick = {
                            onEvent(
                                GameEvent.NextNightSelect
                            )
                        }
                    )
                }
            }
        }

    }
}