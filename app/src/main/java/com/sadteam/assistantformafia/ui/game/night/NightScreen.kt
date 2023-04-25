package com.sadteam.assistantformafia.ui.game.night

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.SelectRoleCard
import com.sadteam.assistantformafia.ui.game.GameEvent
import com.sadteam.assistantformafia.ui.game.GameState
import com.sadteam.assistantformafia.ui.game.NightSelectState
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.BlueDisabledBackground
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.NightStageBackground
import com.sadteam.assistantformafia.ui.theme.secondFontFamily

@Composable
fun NightScreen(
    navController: NavController,
    state: NightSelectState,
    onEvent: (GameEvent) -> Unit,
) {
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.stage) + " " + stringResource(id = R.string.night),
        backgroundColor = NightStageBackground,
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
                    .align(CenterHorizontally),
                painter = painterResource(id = R.drawable.moon),
                contentDescription = ""
            )
            Text(
                text = state.targetRole?.getTranslatedName() + " " + stringResource(id = R.string.target),
                modifier = Modifier
                    .fillMaxWidth(),
                fontFamily = secondFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Divider(
                modifier = Modifier.height(5.dp),
                color = Color.Transparent
            )
            for ((player, role) in state.queuePlayers) {
                SelectRoleCard(
                    backgroundColor = BloodRed,
                    text = player.name.value,
                    mainIcon = painterResource(id = R.drawable.add_a_photo),
                    checked = player == state.targetPlayer,
                    onCheckboxClicked = {
                        if (it) onEvent(GameEvent.SelectNightTarget(player))
                        else onEvent(GameEvent.ClearNightTarget(player))
                    },
                )
            }
        }
        if(state.isEnd) {
            BigButton(
                title = stringResource(id = R.string.start_voting),
                backgroundColor = DarkBlue,
                isDisabled = !state.canNext,
                disabledBackground = BlueDisabledBackground,
                onClick = {
                    // todo day voting
                }
            )
        }
        else {
            BigButton(
                title = stringResource(id = R.string.next),
                backgroundColor = DarkBlue,
                isDisabled = !state.canNext,
                disabledBackground = BlueDisabledBackground,
                onClick = {
                    onEvent(
                        GameEvent.NextNightSelect
                    )
                }
            )
        }
    }
}