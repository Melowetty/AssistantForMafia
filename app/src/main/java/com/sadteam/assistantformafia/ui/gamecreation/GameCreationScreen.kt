package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.IconButton
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.components.SmallButton
import com.sadteam.assistantformafia.ui.theme.*

@Composable
fun GameCreationScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Header()
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
                    PlayersCountButton()
                    MenuButton(
                        icon = painterResource(id = R.drawable.ic_baseline_assignment_ind_24),
                        title = stringResource(id = R.string.roles),
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

/**
 * Шапка с вопросами-ответами и настройками
 *
 */
@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Text(
            text = stringResource(id = R.string.game_creation),
            fontFamily = primaryFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.ic_baseline_settings_24),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            description = stringResource(id = R.string.app_settings),
        )
    }
}

//TODO поменять логику нажатия кнопок (поменять дизайн нажатой кнопки)
@Composable
fun PlayersPopup(
    modifier: Modifier = Modifier,
    isShowed: Boolean = false,
    onClose: () -> Unit,
    onPlayersCountChange: (Int) -> Unit = {}
){
    var playersCount by remember { mutableStateOf(1) }
    val interactionSource = remember { MutableInteractionSource() }
    if (isShowed) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(),
        ) {

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        color = DarkBackground,
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClose)
                    .padding(horizontal = 10.dp)
                    .zIndex(1f),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = DarkGreen, shape = RoundedCornerShape(21))
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = {})
                        .padding(horizontal = 70.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        verticalArrangement = Arrangement.spacedBy(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.players_count),
                            color = Color.White,
                            fontFamily = secondFontFamily,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = modifier
                                    .size(width = 33.dp, height = 33.dp)
                                    .background(color = DarkBlue, shape = CircleShape)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        onClick = {
                                            if (playersCount > 1) {
                                                playersCount -= 1
                                                onPlayersCountChange(playersCount)
                                            }
                                        }),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_remove_24),
                                    contentDescription = "remove",
                                    tint = Color.White
                                )
                            }
                            Text(
                                text = "$playersCount",
                                color = Color.White,
                                fontFamily = secondFontFamily,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Box(
                                modifier = modifier
                                    .size(width = 33.dp, height = 33.dp)
                                    .background(color = DarkBlue, shape = CircleShape)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        onClick = {
                                            playersCount += 1
                                            onPlayersCountChange(playersCount)
                                        }),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_add_24),
                                    contentDescription = "add",
                                    tint = Color.White
                                )
                            }
                        }
                        SmallButton(
                            title = stringResource(id = R.string.save),
                            backgroundColor = BloodRed,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = {
                                        onClose()
                                    })
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlayersCountButton(
    modifier: Modifier = Modifier
) {
    var isPopupShowed by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier) {
        MenuButton(
            icon = painterResource(id = R.drawable.baseline_people_alt_24),
            title = stringResource(id = R.string.players),
            onClick = {
                isPopupShowed = true
            }
        )
        PlayersPopup(
            isShowed = isPopupShowed,
            onClose = {
                isPopupShowed = false
            }
        )
    }
}