package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
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
import com.sadteam.assistantformafia.ui.appsettings.AppSettingsViewModel
import com.sadteam.assistantformafia.ui.tags.SelectCountTags
import com.sadteam.assistantformafia.ui.theme.*
import java.util.*

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
) {
    CustomPopup(
        modifier = modifier,
        title = title,
        isShowed = isShowed,
        onClose = onClose
    ) {
        IconButton(
            painter = painterResource(id = R.drawable.baseline_remove_24),
            size = 33.dp,
            backgroundColor = if(value > min) DarkBlue else Gray,
            iconColor = if(value > min) Color.White else LightGray,
            disabled = value == min,
            description = "remove",
            onClick = onDecreasing,
            modifier = Modifier
                .testTag(SelectCountTags.REMOVE)
        )
        Text(
            text = "$value",
            color = Color.White,
            fontFamily = secondFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .testTag(SelectCountTags.VALUE)
        )
        IconButton(
            painter = painterResource(id = R.drawable.baseline_add_24),
            size = 33.dp,
            backgroundColor = if(value < max) DarkBlue else Gray,
            iconColor = if(value < max) Color.White else LightGray,
            disabled = value == max,
            description = "add",
            onClick = onIncreasing,
            modifier = Modifier
                .testTag(SelectCountTags.ADD)
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
    icon: Painter,
    onIncreasing: () -> Unit,
    onDecreasing: () -> Unit,
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
            modifier = Modifier
                .testTag(SelectCountTags.OPENING_BUTTON),
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
            modifier = Modifier
                .testTag(SelectCountTags.BOX),
            onDecreasing = onDecreasing,
            onIncreasing = onIncreasing,
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
            modifier = Modifier
                .testTag(SelectCountTags.OPENING_BUTTON),
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
            modifier = Modifier
                .testTag(SelectCountTags.BOX),
            onDecreasing = onDecreasing,
            onIncreasing = onIncreasing,
        )
    }
}