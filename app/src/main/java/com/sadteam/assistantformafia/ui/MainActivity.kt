package com.sadteam.assistantformafia.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistantForMafiaTheme {
                // A surface container using the 'background' color from the theme
                //PlayersPopup()
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
        }
    }
}

/**
 * Круглая кнопка с иконкой внутри
 * TODO необходимо сделать действие на кнопку
 *
 * @param painter иконка
 * @param backgroundColor цвет фона-круга сзади иконки
 * @param description описание действия кнопки
 */
@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    backgroundColor: Color,
    description: String,
) {
    Box(
        modifier = modifier
            .width(40.dp)
            .height(40.dp)
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            painter = painter,
            contentDescription = description,
        )
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

/**
 * Кнопка, нажатие на которой вызывает какое-либо действие
 *
 * @param icon иконка-подсказка внури кнопки
 * @param title текст на кнопке
 */
@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    onClick: () -> Unit = {},
){
    val interactionSource = remember { MutableInteractionSource()}
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = BloodRed,
                shape = CircleShape
            )
            .padding(top = 8.dp, end = 10.dp, bottom = 8.dp, start = 20.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)
        )
        Text(
            text = title,
            fontFamily = secondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            contentDescription = stringResource(id = R.string.more_detail),
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)
        )
    }
}

/**
 * Кнопка с кастонмным текстом и фоном
 *
 * @param title текст на кнопке
 * @param backgroundColor цвет фона кнопки
 */
@Composable
fun BigButton(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .padding(top = 16.dp, bottom = 16.dp),
    ){
        Text(
            text = title,
            color = Color.White,
            fontFamily = secondFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
    ){
        Text(
            text = title,
            color = Color.White,
            fontFamily = secondFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
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
    val interactionSource = remember { MutableInteractionSource()}
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