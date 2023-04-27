package com.sadteam.assistantformafia.ui.game.introduction

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.SelectRoleCard
import com.sadteam.assistantformafia.ui.game.DistributionOfRolesState
import com.sadteam.assistantformafia.ui.game.GameEvent
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.BaseRoleBackgroundColor
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.BlueDisabledBackground
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily
import com.sadteam.assistantformafia.ui.theme.secondFontFamily

@Composable
fun IntroductionScreen(
    navController: NavController,
    initialState: GameCreationState,
    state: DistributionOfRolesState,
    onEvent: (GameEvent) -> Unit,
) {
    LaunchedEffect(key1 = Unit, block = {
        onEvent(
            GameEvent.InitGame(initialState = initialState)
        )
    })
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.roles)
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
                            append(stringResource(id = R.string.introduction_text))
                            append("\n")
                            withStyle(
                                style = SpanStyle(
                                    color = state.targetRole?.getTextColor() ?: Color.White,
                                    fontSize = 26.sp
                                ),
                            ) {
                                append(state.targetRole?.getTranslatedName() ?: "None")
                            }
                        }
                    }
                }
            )
            for ((player, isChecked) in state.queuePlayers) {
                val backgroundColor = remember {
                    Animatable(BaseRoleBackgroundColor)
                }

                LaunchedEffect(key1 = isChecked, block = {
                    (if (isChecked) state.targetRole?.getBackgroundColor()
                    else BaseRoleBackgroundColor)?.let { backgroundColor.animateTo(it,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)) }
                })
                SelectRoleCard(
                    backgroundColor = backgroundColor.value,
                    text = player.name.value,
                    mainIcon = painterResource(id = R.drawable.add_a_photo),
                    checked = isChecked,
                    onCheckboxClicked = {
                        if (it) onEvent(GameEvent.SetRole(player, state.targetRole))
                        else onEvent(GameEvent.ClearRole(player))
                    },
                )
            }
        }
        if(state.isEnd) {
            BigButton(
                title = stringResource(id = R.string.start),
                backgroundColor = DarkBlue,
                isDisabled = !state.canNext,
                disabledBackground = BlueDisabledBackground,
                onClick = {
                    navController.navigate(Screen.NightStage.route)
                }
            )
        }
        else {
            BigButton(
                title = stringResource(id = R.string.next),
                backgroundColor = DarkBlue,
                isDisabled = !state.canNext,
                disabledBackground = BlueDisabledBackground,
                onClick = {
                    onEvent(
                        GameEvent.NextSelectRole
                    )
                }
            )
        }
    }
}
