package com.sadteam.assistantformafia.ui.gamecreation.roles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.ValuePicker
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationEvent
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.DisabledSecondaryBackground
import com.sadteam.assistantformafia.ui.theme.SecondaryBackground
import com.sadteam.assistantformafia.utils.IconUtils.Companion.toRoleIcon
import com.sadteam.assistantformafia.utils.Utils

@Composable
fun RolesScreen(
    navController: NavController,
    state: GameCreationState,
    onEvent: (GameCreationEvent) -> Unit,
) {
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.roles)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (pair in state.roles) {
                var roleIcon = pair.key.icon.toRoleIcon()
                val max = Utils.getRoleCountLimit(
                    pair.key,
                    pair.value,
                    state.players.size,
                    state.distributedPlayers
                )
                RoleCard(
                    title = pair.key.getTranslatedName(),
                    icon = roleIcon,
                    value = pair.value,
                    min = pair.key.min,
                    max = max,
                    onDecrease = {
                        onEvent(
                            GameCreationEvent.DecrementRole(pair.key)
                        )
                    },
                    onIncrease = {
                        onEvent(
                            GameCreationEvent.IncrementRole(pair.key)
                        )
                    },
                    backgroundColor = pair.key.getBackgroundColor()
                )
            }
        }
        BigButton(
            title = stringResource(id = R.string.start),
            backgroundColor = SecondaryBackground,
            isDisabled = !state.canStart,
            disabledBackground = DisabledSecondaryBackground,
            onClick = {
                navController.navigate(route = Screen.Introduction.route) {
                    popUpTo(route = Screen.GameCreation.route)
                }
            }
        )
    }
}

@Composable
fun RoleCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageBitmap,
    max: Int,
    min: Int,
    value: Int,
    backgroundColor: Color,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .padding(8.dp)
            ) {
                Image(
                    bitmap = icon,
                    modifier = Modifier
                        .fillMaxSize(),
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "main icon"
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        ValuePicker(
            modifier = Modifier
                .requiredWidth(120.dp),
            value = value,
            max = max,
            min = min,
            onIncreasing = onIncrease,
            onDecreasing = onDecrease,
        )
    }
}



