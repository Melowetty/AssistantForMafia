package com.sadteam.assistantformafia.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.SettingsDescription
import com.sadteam.assistantformafia.ui.theme.SettingsTitle
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily
import com.sadteam.assistantformafia.ui.theme.secondFontFamily

/**
 * Кнопка, нажатие на которой вызывает какое-либо действие
 *
 * @param modifier модификатор элемента
 * @param icon иконка-подсказка внури кнопки
 * @param title текст на кнопке
 * @param currentValue текущее значение
 * @param onClick callback функция, срабатывающая при клике на кнопку
 */
@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    icon: ImageBitmap,
    title: String,
    currentValue: String? = null,
    onClick: () -> Unit = {},
){
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .bounceClick()
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                bitmap = icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
            )
            Text(
                text = title,
                fontFamily = secondFontFamily,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(currentValue != null) Text(
                text = currentValue,
                fontFamily = secondFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                tint = Color.White,
                contentDescription = stringResource(id = R.string.more_detail),
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
            )
        }
    }
}

/**
 * Кнопка, нажатие на которой вызывает какое-либо действие
 *
 * @param modifier модификатор элемента
 * @param icon иконка-подсказка внури кнопки
 * @param title текст на кнопке
 * @param currentValue текущее значение
 * @param onClick callback функция, срабатывающая при клике на кнопку
 */
@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    currentValue: String? = null,
    onClick: () -> Unit = {},
){
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .bounceClick()
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
            )
            Text(
                text = title,
                fontFamily = secondFontFamily,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(currentValue != null) Text(
                text = currentValue,
                fontFamily = secondFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                tint = Color.White,
                contentDescription = stringResource(id = R.string.more_detail),
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
            )
        }
    }
}

/**
 * Кнопка-меню с контентом внутри
 *
 * @param modifier модификатор элемента
 * @param icon Иконка для кнопки
 * @param title Заголовок кнопки
 * @param onClick callback-функция при нажатии
 * @param content composable-контент
 */
@Composable
fun ExtendedMenuButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 20.dp, bottom = 8.dp, start = 20.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = title,
                    color = SettingsTitle,
                    fontFamily = primaryFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                content()
            }
        }
    }
}

/**
 * Кнопка-меню с контентом внутри
 *
 * @param modifier модификатор элемента
 * @param icon Иконка для кнопки
 * @param title Заголовок кнопки
 * @param onClick callback-функция при нажатии
 * @param currentValue текущее значение
 */
@Composable
fun ExtendedMenuButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    onClick: () -> Unit = {},
    currentValue: String? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 10.dp, bottom = 8.dp, start = 20.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = title,
                    color = SettingsTitle,
                    fontFamily = primaryFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                if(currentValue != null) {
                    Text(
                        text = currentValue,
                        color = SettingsDescription,
                        fontFamily = primaryFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


enum class ButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.9f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {  }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

/**
 * Большая кнопка с пользовательским текстом и фоном
 *
 * @param modifier модификатор элемента
 * @param title текст на кнопке
 * @param backgroundColor цвет фона кнопки
 * @param onClick коллбэк функция при нажатии на кнопку
 * @param isDisabled если включено, то кнопка не срабатывает на нажатия
 */
@Composable
fun BigButton(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color,
    onClick: () -> Unit = {},
    isDisabled: Boolean = false,
    disabledBackground: Color = Color.Gray,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .bounceClick()
            .background(
                color = if(!isDisabled) backgroundColor else disabledBackground,
                shape = CircleShape
            )
            .padding(top = 16.dp, bottom = 16.dp)

            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (!isDisabled) onClick()
                }
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

@Composable
@Preview
fun BigButtonPreview() {
    BigButton(
        title = "Next",
        backgroundColor = DarkBlue,
    )
}

/**
 * Маленькая кнопка с пользовательским текстом и фоном
 *
 * @param modifier модификатор элемента
 * @param title текст на кнопке
 * @param backgroundColor цвет фона кнопки
 */
@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .bounceClick()
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

/**
 * Круглая кнопка с иконкой внутри
 *
 * @param modifier модификатор элемента
 * @param size размер иконки
 * @param painter иконка
 * @param backgroundColor цвет фона-круга сзади иконки
 * @param iconColor цвет иконки
 * @param description описание действия кнопки
 * @param disabled отключена кнопка или нет
 * @param onClick callback функция, срабатывающая при нажатии на кнопку
 */
@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    size: Dp,
    painter: Painter,
    backgroundColor: Color,
    iconColor: Color? = null,
    description: String,
    disabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .width(size)
            .height(size)
            .bounceClick()
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (!disabled) onClick()
                }
            ),
        contentAlignment = Alignment.Center

    ) {
        if (iconColor != null) {
            Icon(
                painter = painter,
                contentDescription = description,
                tint = iconColor,
            )
        }
        else {
            Icon(
                painter = painter,
                contentDescription = description,
            )
        }
    }
}

/**
 * Кнопка назад, которая видна если можно уйти назад
 *
 * @param modifier модификатор элемента
 * @param navController навигационный контроллер, для проверки возможности уйти назад
 * @param onClick callback-функция при нажатии назад
 */
@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavController,
    onClick: () -> Unit = {}
) {
    if (navController.backQueue.size > 2)
    {
        val interactionSource = remember { MutableInteractionSource() }
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = "Back",
            tint = Color.White,
            modifier = modifier
                .bounceClick()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        navController.popBackStack()
                        onClick()
                    }
                ),
        )
    }
    else {
        Spacer(modifier = Modifier
            .height(24.dp)
            .width(24.dp))
    }
}