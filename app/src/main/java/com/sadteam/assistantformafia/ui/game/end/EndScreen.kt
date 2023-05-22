package com.sadteam.assistantformafia.ui.game.end

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.game.EndGameState
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.*

@Composable
fun EndScreen(
    navController: NavController,
    state: EndGameState
) {
    InnocentsWon(
        navController = navController,
        onBackToMenu = {
            // todo clear game state
            navController.navigate(route = Screen.GameCreation.route) {
                popUpTo(route = Screen.GameCreation.route)
            }
        }
    )
}

@Composable
fun EnemyWon(
    navController: NavController,
    onBackToMenu: () -> Unit,
) {
    BaseWinLayout(
        navController = navController,
        image = painterResource(id = R.drawable.enemy_won),
        backgroundColor = NightStageBackground,
        roleColor = EnemyRoleTextColor,
        role = "Mafia",
        onBackToMenu = onBackToMenu,
        backgroundImage = painterResource(id = R.drawable.moon)
    )
}

@Composable
fun InnocentsWon(
    navController: NavController,
    onBackToMenu: () -> Unit,
) {
    BaseWinLayout(
        navController = navController,
        image = painterResource(id = R.drawable.enemy_defeat),
        backgroundColor = DayStageBackground,
        roleColor = CommonRoleTextColor,
        role = "Innocents",
        onBackToMenu = onBackToMenu,
        backgroundImage = painterResource(id = R.drawable.sun)
    )
}

@Composable
fun BaseWinLayout(
    navController: NavController,
    image: Painter,
    backgroundColor: Color,
    backgroundImage: Painter,
    role: String,
    roleColor: Color,
    onBackToMenu: () -> Unit
) {
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
                    .align(Alignment.CenterHorizontally),
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
                                    // todo out winner role
                                    append(role)
                                }
                                // todo translate
                                append(" won")
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