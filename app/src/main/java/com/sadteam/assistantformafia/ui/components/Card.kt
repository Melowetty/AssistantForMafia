package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.DarkBlue

/**
 * Карточка с именем и иконками
 *
 * @param modifier модификатор
 * @param text текст на карточке
 * @param mainIcon главная иконка (слева)
 * @param secondIcon вспомогательная иконка (справа)
 * @param onClick callback-функция срабатывающая при клике на карточку
 * @param onMainIconClick callback-функция срабатывающая при клике на главную иконку
 * @param onSecondIconClick callback-функция срабатывающая при клике на вспомогательную икноку
 */
@Composable
fun Card(
    modifier: Modifier = Modifier,
    text: String,
    mainIcon: Painter,
    secondIcon: Painter,
    onClick: () -> Unit = {},
    onMainIconClick: () -> Unit = {},
    onSecondIconClick: () -> Unit = {},
) {
    Card(
        modifier = modifier,
        content = {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        },
        mainIcon = mainIcon,
        secondIcon = secondIcon,
        onClick = onClick,
        onMainIconClick = onMainIconClick,
        onSecondIconClick = onSecondIconClick
    )
}

/**
 * Карточка с именем и иконками
 *
 * @param modifier модификатор
 * @param content контент в карточке
 * @param mainIcon главная иконка (слева)
 * @param secondIcon вспомогательная иконка (справа)
 * @param onClick callback-функция срабатывающая при клике на карточку
 * @param onMainIconClick callback-функция срабатывающая при клике на главную иконку
 * @param onSecondIconClick callback-функция срабатывающая при клике на вспомогательную икноку
 */
@Composable
fun Card(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
    mainIcon: Painter,
    secondIcon: Painter,
    onClick: () -> Unit = {},
    onMainIconClick: () -> Unit = {},
    onSecondIconClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = BloodRed,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick()
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(
                        color = DarkBlue,
                        CircleShape,
                    )
                    .padding(12.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            onMainIconClick()
                        }
                    ),
            ) {
                Icon(
                    painter = mainIcon,
                    contentDescription = "main icon"
                )
            }
            content()
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = Color.Transparent,
                    CircleShape,
                )
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        onSecondIconClick()
                    }
                ),
        ) {
            Icon(
                painter = secondIcon,
                contentDescription = "second icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
    }
}