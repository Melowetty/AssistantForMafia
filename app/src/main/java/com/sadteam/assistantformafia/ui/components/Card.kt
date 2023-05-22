package com.sadteam.assistantformafia.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Effect
import com.sadteam.assistantformafia.ui.theme.*
import com.sadteam.assistantformafia.utils.Utils

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
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick()
                }
            )
            .padding(horizontal = 10.dp, vertical = 4.dp),
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
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        onSecondIconClick()
                    }
                )
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
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
    description: String = "",
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
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                )
                if(description.isNotEmpty()) {
                    Text(
                        text = description,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.White,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
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

@Preview
@Composable
fun PreviewVotingPlayerCard() {
    VotingPlayerCard(
        name = "Player 1",
        photo = Utils.getBitmapFromImage(LocalContext.current, R.drawable.add_a_photo).asImageBitmap(),
        role = "Mafia",
        backgroundColor = EnemyRoleBackgroundColor,
        effects = listOf(Effect.KILL, Effect.LOVE)
    )
}

@Composable
fun VotingPlayerCard(
    modifier: Modifier = Modifier,
    name: String,
    photo: ImageBitmap,
    role: String,
    backgroundColor: Color,
    effects: List<Effect>,
    isEnabled: Boolean = true,
    canBeVoted: Boolean = true,
    value: Int = 0,
    max: Int = Int.MAX_VALUE,
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {},
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isEnabled) backgroundColor else BaseRoleBackgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                if (effects.size > 1) isExpanded = isExpanded.not()
            }
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 200,
                    easing = LinearOutSlowInEasing,
                )
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
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
                ) {
                    Image(
                        bitmap = photo,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "player image"
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.3f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = role,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.White,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AnimatedVisibility(
                    visible = effects.isNotEmpty() && isExpanded.not(),
                    enter = fadeIn(animationSpec =
                    tween(durationMillis = 200)
                    ),
                    exit = fadeOut(animationSpec =
                    tween(durationMillis = 200)
                    )
                ) {
                    EffectIcon(effects.last())
                }
                AnimatedVisibility(
                    visible = effects.size > 1 && isExpanded.not(),
                    enter = fadeIn(animationSpec =
                    tween(durationMillis = 200)
                    ),
                    exit = fadeOut(animationSpec =
                    tween(durationMillis = 200)
                    )
                ) {
                        Text(
                            text = "...",
                            fontSize = 20.sp,
                            color = LightGray,
                            fontWeight = FontWeight.Bold,
                        )
                }

                Spacer(modifier = Modifier
                    .size(5.dp)
                )
                if (canBeVoted) {
                    ValuePicker(
                        modifier = Modifier
                            .requiredWidth(120.dp),
                        value = value,
                        max = max,
                        onIncreasing = onIncrease,
                        onDecreasing = onDecrease,
                    )
                }
            }
        }
        if (isExpanded) {
            Spacer(modifier = Modifier
                .size(5.dp)
            )
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(id = R.string.effects)}: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier
                    .size(4.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(effects) {effect: Effect ->
                        EffectIcon(effect)
                    }
                }
            }
        }
    }
}