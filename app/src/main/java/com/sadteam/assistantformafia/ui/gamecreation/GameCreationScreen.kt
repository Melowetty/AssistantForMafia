package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.BigButton
import com.sadteam.assistantformafia.ui.components.IconButton
import com.sadteam.assistantformafia.ui.components.MenuButton
import com.sadteam.assistantformafia.ui.components.SelectCountButton
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily

/**
 * Экран создания игры
 */
@Composable
fun GameCreationScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Header()
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
                    SelectCountButton(
                        title = stringResource(id = R.string.players_count),
                        minCount = 1
                    )
                    MenuButton(
                        icon = painterResource(id = R.drawable.ic_baseline_assignment_ind_24),
                        title = stringResource(id = R.string.roles),
                    )
                    MenuButton(
                        icon = painterResource(id = R.drawable.baseline_tune_24),
                        title = stringResource(id = R.string.game_settings),
                    )
                    MenuButton(
                        icon = painterResource(id = R.drawable.baseline_help_24),
                        title = stringResource(id = R.string.game_rules),
                    )
                }
                BigButton(
                    title = stringResource(id = R.string.start),
                    backgroundColor = DarkBlue)
            }
        }
    }
}

/**
 * Шапка с вопросами-ответами и настройками
 * @param modifier модификатор элемента
 */
@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Text(
            text = stringResource(id = R.string.game_creation),
            fontFamily = primaryFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.ic_baseline_settings_24),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            description = stringResource(id = R.string.app_settings),
        )
    }
}
