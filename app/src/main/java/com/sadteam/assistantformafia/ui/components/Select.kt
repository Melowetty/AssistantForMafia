package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter

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
        ValuePicker(
            value = value,
            min = min,
            max = max,
            onIncreasing = onIncreasing,
            onDecreasing = onDecreasing,
        )
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
    popupModifier: Modifier = Modifier.wrapContentHeight(),
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
            modifier = popupModifier,
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
    popupModifier: Modifier = Modifier.wrapContentHeight(),
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
            modifier = popupModifier,
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