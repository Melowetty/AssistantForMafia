package com.sadteam.assistantformafia.ui.game.introduction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.Card
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.SelectRoleCard
import com.sadteam.assistantformafia.ui.game.DistributionOfRolesState
import com.sadteam.assistantformafia.ui.game.GameEvent
import com.sadteam.assistantformafia.ui.game.GameState
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.BlueDisabledBackground
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.secondFontFamily

@Composable
fun IntroductionScreen(
    navController: NavController,
    initialState: GameCreationState,
    state: DistributionOfRolesState,
    onEvent: (GameEvent) -> Unit,
) {
    LaunchedEffect(key1 = Unit, block = {
        onEvent(
            GameEvent.InitGame(initialState = initialState)
        )
    })
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.roles)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.introduction_text) + " " + state.targetRole?.getTranslatedName(),
                modifier = Modifier
                    .fillMaxWidth(),
                fontFamily = secondFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            for ((player, checked) in state.queuePlayers) {
                SelectRoleCard(
                    backgroundColor = BloodRed,
                    text = player.name.value,
                    mainIcon = painterResource(id = R.drawable.add_a_photo),
                    checked = checked,
                    onCheckboxClicked = {
                        if (it) onEvent(GameEvent.SetRole(player, state.targetRole))
                        else onEvent(GameEvent.ClearRole(player))
                    },
                )
            }
        }
        if(state.isEnd) {
            BigButton(
                title = stringResource(id = R.string.start),
                backgroundColor = DarkBlue,
                isDisabled = !state.canNext,
                disabledBackground = BlueDisabledBackground,
                onClick = {
                    onEvent(
                        GameEvent.StartGame
                    )
                    navController.navigate(Screen.NightStage.route)
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
                        GameEvent.NextSelectRole
                    )
                }
            )
        }
    }
}
