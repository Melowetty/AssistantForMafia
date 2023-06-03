package com.sadteam.assistantformafia.ui.game.end

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.data.models.RoleType
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.game.EndGameState
import com.sadteam.assistantformafia.ui.game.GameEvent
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.DayStageBackground
import com.sadteam.assistantformafia.ui.theme.NightStageBackground
import com.sadteam.assistantformafia.ui.theme.SecondaryBackground
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily
import com.sadteam.assistantformafia.utils.Utils

@Composable
fun EndScreen(
    navController: NavController,
    state: EndGameState,
    onEvent: (GameEvent) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(
            GameEvent.EndGame
        )
    }
    if (state.roleWin?.roleType == RoleType.ENEMY) {
        EnemyWon(
            navController = navController,
            onBackToMenu = {
                navController.navigate(route = Screen.GameCreation.route) {
                    popUpTo(route = Screen.GameCreation.route) {
                        inclusive = true
                    }
                }
            },
            role = state.roleWin,
        )
    } else {
        InnocentsWon(
            navController = navController,
            role = state.roleWin!!,
            onBackToMenu = {
                navController.navigate(route = Screen.GameCreation.route) {
                    popUpTo(route = Screen.GameCreation.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

@Composable
private fun EnemyWon(
    navController: NavController,
    role: Role,
    onBackToMenu: () -> Unit,
) {
    BaseWinLayout(
        navController = navController,
        image = painterResource(id = R.drawable.enemy_won),
        backgroundColor = NightStageBackground,
        roleColor = role.getTextColor(),
        winMessage = role.getTranslatedWinMessage(),
        onBackToMenu = onBackToMenu,
        backgroundImage = painterResource(id = R.drawable.moon)
    )
}

@Composable
private fun InnocentsWon(
    navController: NavController,
    role: Role,
    onBackToMenu: () -> Unit,
) {
    BaseWinLayout(
        navController = navController,
        image = painterResource(id = R.drawable.enemy_defeat),
        backgroundColor = DayStageBackground,
        roleColor = role.getTextColor(),
        winMessage = role.getTranslatedWinMessage(),
        onBackToMenu = onBackToMenu,
        backgroundImage = painterResource(id = R.drawable.sun_large)
    )
}

@Composable
private fun BaseWinLayout(
    navController: NavController,
    image: Painter,
    backgroundColor: Color,
    backgroundImage: Painter,
    roleColor: Color,
    winMessage: String,
    onBackToMenu: () -> Unit
) {
    val (role, message) = Utils.getColoredMessage(winMessage)
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.the_end),
        backgroundColor = backgroundColor,
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(84.dp),
                painter = backgroundImage,
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    softWrap = true,
                    text = buildAnnotatedString {
                        withStyle(style = ParagraphStyle(lineHeight = 36.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 36.sp,
                                    fontFamily = primaryFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                )
                            ) {
                                withStyle(
                                    style = SpanStyle(
                                        color = roleColor,
                                    ),
                                ) {
                                    append(role)
                                }
                                append(message)
                            }
                        }
                    }
                )
                Image(modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                    painter = image,
                    contentDescription = null,
                )
                BigButton(
                    title = stringResource(id = R.string.back_to_menu),
                    backgroundColor = SecondaryBackground,
                    onClick = onBackToMenu
                )
            }
        }
    }
}