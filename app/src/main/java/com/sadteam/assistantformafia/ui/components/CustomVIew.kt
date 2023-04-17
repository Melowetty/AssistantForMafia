package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.DarkBackground
import com.sadteam.assistantformafia.ui.theme.DarkGreen
import com.sadteam.assistantformafia.ui.theme.secondFontFamily

/**
 * Всплывающее окно с выбором количества игроков
 * TODO поменять логику нажатия кнопок (поменять дизайн нажатой кнопки)
 *
 * @param modifier модификатор элемента
 * @param title Заголовок окна
 * @param isShowed показано ли окно
 * @param onClose callback функция, срабатывающая при попытки закрытия окна
 */
@Composable
fun CustomPopup(
    modifier: Modifier = Modifier,
    title: String,
    isShowed: Boolean = false,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    if (isShowed) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(focusable = true),
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
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = DarkGreen, shape = RoundedCornerShape(10))
                        .padding(vertical = 10.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = {}
                        ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier
                            .size(33.dp)
                        )
                        Text(
                            text = title,
                            color = Color.White,
                            fontFamily = secondFontFamily,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "close popup",
                            modifier = Modifier
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = onClose
                                )
                        )
                    }
                    Spacer(modifier = Modifier.size(width = 0.dp, height = 18.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        content()
                        SmallButton(
                            title = stringResource(id = R.string.save),
                            backgroundColor = BloodRed,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = onClose
                                )
                                .padding(horizontal = 40.dp)
                        )
                    }
                }
            }
        }
    }
}