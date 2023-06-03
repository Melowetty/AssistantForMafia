package com.sadteam.assistantformafia.ui.game.handshake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.SelectRoleCard
import com.sadteam.assistantformafia.ui.game.GameEvent
import com.sadteam.assistantformafia.ui.game.HandshakeState
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.*
import com.sadteam.assistantformafia.utils.Utils

@Composable
fun HandshakeScreen(
    navController: NavController,
    state: HandshakeState,
    onEvent: (GameEvent) -> Unit,
) {
    LaunchedEffect(key1 = state.gameIsEnd) {
        if (state.gameIsEnd) {
            navController.navigate(Screen.EndStage.route) {
                popUpTo(route = Screen.GameCreation.route)
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        onEvent(
            GameEvent.StartHandshake
        )
    }
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.stage) + " " + stringResource(id = R.string.handshake),
        backgroundColor = DayStageBackground,
        backgroundContent = {
            Row(modifier = Modifier
                .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.city),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(84.dp),
                painter = painterResource(id = R.drawable.sun_large),
                contentDescription = ""
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.selecting_time),
                fontSize = 24.sp,
                fontFamily = primaryFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.size(5.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(state.players){index: Int, player: Player ->
                        SelectRoleCard(
                            backgroundColor = player.role?.getBackgroundColor()?: BaseRoleBackgroundColor,
                            text = player.name.value,
                            description = player.role?.getTranslatedName() ?: "",
                            mainIcon = player.icon.value?: Utils.getBitmapFromImage(LocalContext.current, R.drawable.add_a_photo).asImageBitmap(),
                            mainIconModifier = if(player.icon.value != null) Modifier else Modifier.padding(8.dp),
                            checked = index == state.targetPlayerIndex,
                            onCheckboxClicked = {
                                if (it) onEvent(GameEvent.SelectHandshakeTarget(index))
                                else onEvent(GameEvent.ClearHandshakeTarget)
                            },
                        )
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                BigButton(
                    title = stringResource(id = R.string.kick),
                    backgroundColor = SecondaryBackground,
                    isDisabled = !state.canKick,
                    disabledBackground = DisabledSecondaryBackground,
                    onClick = {
                        onEvent(
                            GameEvent.KickHandshakeTarget
                        )
                    }
                )
            }
        }
    }
}