package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.Gray
import com.sadteam.assistantformafia.ui.theme.LightGray
import com.sadteam.assistantformafia.ui.theme.secondFontFamily

/**
* Всплывающее окно с выбором количества игроков
* TODO поменять логику нажатия кнопок (поменять дизайн нажатой кнопки)
*
* @param modifier модификатор элемента
* @param title Заголовок окна
* @param min минимальное число
* @param max максимальное число
* @param isShowed показано ли окно
* @param onClose callback функция, срабатывающая при попытки закрытия окна
* @param onIncreasing callback увеличения значения
* @param onDecreasing callback уменьшения значения
*/
@Composable
fun SelectCountPopup(
    modifier: Modifier = Modifier,
    title: String,
    value: Int,
    min: Int,
    max: Int,
    isShowed: Boolean = false,
    onClose: () -> Unit,
    onIncreasing: () -> Unit,
    onDecreasing: () -> Unit,
    content: @Composable (() -> Unit) = {},
) {
    CustomPopup(
        modifier = modifier,
        title = title,
        isShowed = isShowed,
        onClose = onClose
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                painter = painterResource(id = R.drawable.baseline_remove_24),
                size = 33.dp,
                backgroundColor = if (value > min) DarkBlue else Gray,
                iconColor = if (value > min) Color.White else LightGray,
                disabled = value == min,
                description = "remove",
                onClick = onDecreasing,
            )
            Text(
                text = "$value",
                color = Color.White,
                fontFamily = secondFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            IconButton(
                painter = painterResource(id = R.drawable.baseline_add_24),
                size = 33.dp,
                backgroundColor = if (value < max) DarkBlue else Gray,
                iconColor = if (value < max) Color.White else LightGray,
                disabled = value == max,
                description = "add",
                onClick = onIncreasing,
            )
        }
        content()
    }
}

/**
 * Кнопка-меню, нажатие на которую вызывает показ всплывающего окна с выбором количества игроков
 *
 * @param modifier модификатор элемента
 * @param title заголовок для кнопки
 * @param icon иконка для кнопки
 * @param min минимальное число
 * @param max максимальное число
 * @param onIncreasing callback увеличения значения
 * @param onDecreasing callback уменьшения значения
 */
@Composable
fun SelectCount(
    modifier: Modifier = Modifier,
    title: String,
    value: Int,
    min: Int = Int.MIN_VALUE,
    max: Int = Int.MAX_VALUE,
    icon: Painter,
    onIncreasing: () -> Unit,
    onDecreasing: () -> Unit,
    content: @Composable (() -> Unit) = {},
) {
    var isPopupShowed by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier) {
        MenuButton(
            icon = icon,
            title = title,
            onClick = {
                isPopupShowed = true
            },
            currentValue = value.toString()
        )
        SelectCountPopup(
            title = title,
            value = value,
            min = min,
            max = max,
            isShowed = isPopupShowed,
            onClose = {
                isPopupShowed = false
            },
            onDecreasing = onDecreasing,
            onIncreasing = onIncreasing,
            content = content,
        )
    }
}

/**
 * Кнопка-меню, нажатие на которую вызывает показ всплывающего окна с выбором количества игроков
 *
 * @param modifier модификатор элемента
 * @param title заголовок для кнопки
 * @param icon иконка для кнопки
 * @param min минимальное число
 * @param max максимальное число
 * @param onIncreasing callback увеличения значения
 * @param onDecreasing callback уменьшения значения
 */
@Composable
fun SelectCount(
    modifier: Modifier = Modifier,
    title: String,
    value: Int,
    min: Int = Int.MIN_VALUE,
    max: Int = Int.MAX_VALUE,
    icon: ImageBitmap,
    onIncreasing: () -> Unit,
    onDecreasing: () -> Unit,
    content: @Composable () -> Unit = {},
) {
    var isPopupShowed by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier) {
        MenuButton(
            icon = icon,
            title = title,
            onClick = {
                isPopupShowed = true
            },
            currentValue = value.toString()
        )
        SelectCountPopup(
            title = title,
            value = value,
            min = min,
            max = max,
            isShowed = isPopupShowed,
            onClose = {
                isPopupShowed = false
            },
            onDecreasing = onDecreasing,
            onIncreasing = onIncreasing,
            content = content,
        )
    }
}