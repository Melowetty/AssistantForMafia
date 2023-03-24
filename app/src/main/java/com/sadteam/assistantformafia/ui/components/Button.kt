package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.theme.BloodRed
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
    icon: Painter,
    title: String,
    currentValue: String? = null,
    onClick: () -> Unit = {},
){
    val interactionSource = remember { MutableInteractionSource() }
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
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
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(currentValue != null) Text(
                text = currentValue,
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
}

@Composable
fun ExtendedMenuButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    onClick: () -> Unit = {},
    content: @Composable() (() -> (Unit)),
) {
    val interactionSource = remember { MutableInteractionSource() }
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = title,
                    fontFamily = secondFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                content()
            }
        }
    }
}

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
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = title,
                    fontFamily = secondFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                if(currentValue != null) Text(
                    text = currentValue,
                    fontFamily = secondFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
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
 * @param painter иконка
 * @param backgroundColor цвет фона-круга сзади иконки
 * @param description описание действия кнопки
 * @param onClick callback функция, срабатывающая при нажатии на кнопку
 */
@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    backgroundColor: Color,
    description: String,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .width(40.dp)
            .height(40.dp)
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            painter = painter,
            contentDescription = description,
        )
    }
}

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
            modifier = modifier
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