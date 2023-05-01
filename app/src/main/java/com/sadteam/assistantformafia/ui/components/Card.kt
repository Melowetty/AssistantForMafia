package com.sadteam.assistantformafia.ui.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
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
    mainIcon: ImageBitmap,
    mainIconModifier: Modifier = Modifier,
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
        mainIconModifier = mainIconModifier,
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
    mainIcon: ImageBitmap,
    mainIconModifier: Modifier = Modifier,
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
            .padding(horizontal = 10.dp, vertical = 4.dp)
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
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(
                        color = DarkBlue,
                        CircleShape,
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            onMainIconClick()
                        }
                    ),
            ) {
                Image(
                    bitmap = mainIcon,
                    modifier = mainIconModifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds,
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
                tint = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

/**
 * Карточка с именем и иконками
 *
 * @param modifier модификатор
 * @param text текст на карточке
 * @param mainIcon главная иконка (слева)
 * @param checked значение чекбокса
 * @param onCheckboxClicked callback-функция при клике на чекбокс
 */
@Composable
fun SelectRoleCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    text: String,
    mainIcon: ImageBitmap,
    mainIconModifier: Modifier = Modifier,
    checked: Boolean = false,
    onMainIconClick: () -> Unit = {},
    onCheckboxClicked: (Boolean) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .toggleable(
                value = checked,
                role = Role.Checkbox,
                onValueChange = onCheckboxClicked
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(
                        color = DarkBlue,
                        CircleShape,
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            onMainIconClick()
                        }
                    ),
            ) {
                Image(
                    bitmap = mainIcon,
                    modifier = mainIconModifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "main icon"
                )
            }
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckboxClicked,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.White,
                checkmarkColor = BloodRed
            )
        )
    }
}

@Composable
fun AnimatedPlayerCard(
    startBackgroundColor: Color,
    backgroundColor: Color,
    isChecked: Boolean,
    text: String,
    mainIcon: ImageBitmap,
    mainIconModifier: Modifier = Modifier,
    onCheckboxClicked: (Boolean) -> Unit,
) {
    val animatedBackgroundColor = remember {
        Animatable(startBackgroundColor)
    }

    LaunchedEffect(key1 = isChecked, block = {
        animatedBackgroundColor.animateTo(
            if (isChecked) backgroundColor else startBackgroundColor,
            animationSpec = tween(400, easing = FastOutSlowInEasing
            )
        )
    })
    SelectRoleCard(
        backgroundColor = animatedBackgroundColor.value,
        text = text,
        mainIcon = mainIcon,
        mainIconModifier = mainIconModifier,
        checked = isChecked,
        onCheckboxClicked = onCheckboxClicked,
    )
}