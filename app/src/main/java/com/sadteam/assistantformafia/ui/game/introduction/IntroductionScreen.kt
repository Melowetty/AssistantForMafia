package com.sadteam.assistantformafia.ui.game.introduction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
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
import com.sadteam.assistantformafia.ui.components.AnimatedPlayerCard
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.game.DistributionOfRolesState
import com.sadteam.assistantformafia.ui.game.GameEvent
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.BaseRoleBackgroundColor
import com.sadteam.assistantformafia.ui.theme.DisabledSecondaryBackground
import com.sadteam.assistantformafia.ui.theme.SecondaryBackground
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily
import com.sadteam.assistantformafia.utils.Utils

@Composable
fun IntroductionScreen(
    navController: NavController,
    initialState: GameCreationState,
    state: DistributionOfRolesState,
    onEvent: (GameEvent) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        onEvent(
            GameEvent.InitGame(
                context = context,
                initialState = initialState
            )
        )
    })
    MainLayout(
        navController = navController,
        title = "${stringResource(id = R.string.stage)} ${stringResource(id = R.string.introduction)}"
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 32.sp)) {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontFamily = primaryFontFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        ) {
                            append(context.getString(R.string.introduction_text))
                            append("\n")
                            withStyle(
                                style = SpanStyle(
                                    color = state.targetRole?.getTextColor() ?: Color.White,
                                    fontSize = 26.sp
                                ),
                            ) {
                                append(state.targetRole?.getTranslatedName() ?: "None")
                                append(" (${state.maxCount})")
                            }
                        }
                    }
                }
            )
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(state.queuePlayers) {_: Int, item: Player ->
                        AnimatedPlayerCard(
                            startBackgroundColor = BaseRoleBackgroundColor,
                            backgroundColor = state.targetRole?.getBackgroundColor() ?: BaseRoleBackgroundColor,
                            text = item.name.value,
                            mainIcon = item.icon.value?: Utils.getBitmapFromImage(LocalContext.current, R.drawable.baseline_photo_camera_24).asImageBitmap(),
                            mainIconModifier = if(item.icon.value != null) Modifier else Modifier.padding(8.dp),
                            isChecked = item.isSelected,
                            onCheckboxClicked = {
                                if (it) onEvent(GameEvent.SetRole(item, state.targetRole))
                                else onEvent(GameEvent.ClearRole(item))
                            },
                        )
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                if(state.isEnd) {
                    BigButton(
                        title = stringResource(id = R.string.start),
                        backgroundColor = SecondaryBackground,
                        isDisabled = !state.canNext,
                        disabledBackground = DisabledSecondaryBackground,
                        onClick = {
                            GameEvent.StartGame
                            navController.navigate(Screen.NightStage.route) {
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
                                GameEvent.NextSelectRole
                            )
                        }
                    )
                }
            }
        }
    }
}
