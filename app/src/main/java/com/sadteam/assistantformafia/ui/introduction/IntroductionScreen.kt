package com.sadteam.assistantformafia.ui.introduction

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.Card
import com.sadteam.assistantformafia.ui.components.Header
import com.sadteam.assistantformafia.ui.navigation.Screen
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.secondFontFamily

@Composable
fun IntroductionScreen(
    navController: NavController,
) {
    AssistantForMafiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Header(
                    title = stringResource(id = R.string.roles),
                    navController = navController
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 30.dp,
                            end = 10.dp,
                            bottom = 30.dp,
                            start = 10.dp
                        ),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.introduction_text) + " Mafia",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontFamily = secondFontFamily,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Card(
                            text = "Денис",
                            mainIcon = painterResource(id = R.drawable.add_a_photo),
                        )
                        Card(
                            text = "Миша",
                            mainIcon = painterResource(id = R.drawable.add_a_photo),
                        )
                        Card(
                            text = "Антон",
                            mainIcon = painterResource(id = R.drawable.add_a_photo),
                        )
                    }
                    BigButton(
                        title = stringResource(id = R.string.next),
                        backgroundColor = DarkBlue,
                        onClick = {
                            navController.navigate(route = Screen.Roles.route)
                        }
                    )
                }
            }
        }
    }
}