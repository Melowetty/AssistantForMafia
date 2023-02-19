package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.components.SmallButton
import com.sadteam.assistantformafia.ui.theme.*

/**
 * Кнопка-меню, нажатие на которую вызывает показ всплывающего окна с выбором количества игроков
 *
 * @param modifier модификатор элемента
 */
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
            },
            modifier = Modifier
                .testTag(SelectPlayersCountTags.OPENING_BUTTON)
        )
        PlayersPopup(
            isShowed = isPopupShowed,
            onClose = {
                isPopupShowed = false
            },
            modifier = Modifier
                .testTag(SelectPlayersCountTags.BOX)
        )
    }
}

/**
 * Всплывающее окно с выбором количесвта игроков
 * TODO поменять логику нажатия кнопок (поменять дизайн нажатой кнопки)
 *
 * @param modifier модификатор элемента
 * @param isShowed показано ли окно
 * @param onClose callback функция, срабатывающая при попытки закрытия окна
 * @param onPlayersCountChange callback функция, срабатывающая при измненении количества игроков
 */
@Composable
fun PlayersPopup(
    modifier: Modifier = Modifier,
    isShowed: Boolean = false,
    onClose: () -> Unit,
    onPlayersCountChange: (Int) -> Unit = {}
) {
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
                        onClick = onClose
                    )
                    .padding(horizontal = 10.dp)
                    .zIndex(1f),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
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
                        modifier = Modifier
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
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
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
                                        })
                                    .testTag(SelectPlayersCountTags.REMOVE),
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
                                modifier = Modifier
                                    .testTag(SelectPlayersCountTags.VALUE)
                            )
                            Box(
                                modifier = Modifier
                                    .size(width = 33.dp, height = 33.dp)
                                    .background(color = DarkBlue, shape = CircleShape)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        onClick = {
                                            playersCount += 1
                                            onPlayersCountChange(playersCount)
                                        })
                                    .testTag(SelectPlayersCountTags.ADD),
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
                                .testTag(SelectPlayersCountTags.SAVE)
                        )
                    }
                }
            }
        }
    }
}