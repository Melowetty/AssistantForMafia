package com.sadteam.assistantformafia.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.theme.AssistantForMafiaTheme
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistantForMafiaTheme {
                // A surface container using the 'background' color from the theme
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
                                .padding(top = 30.dp, end = 10.dp, bottom = 30.dp, start = 10.dp),
                        ){
                            MenuButton(
                                icon = painterResource(id = R.drawable.baseline_people_alt_24),
                                title = stringResource(id = R.string.players)
                            )
                            MenuButton(
                                icon = painterResource(id = R.drawable.ic_baseline_assignment_ind_24),
                                title = stringResource(id = R.string.roles)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Круглая кнопка с иконкой внутри
 * TODO необходимо сделать действие на кнопку
 *
 * @param painter иконка
 * @param backgroundColor цвет фона-круга сзади иконки
 * @param description описание действия кнопки
 */
@Composable
fun IconButton(
    painter: Painter,
    backgroundColor: Color,
    description: String,
) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            painter = painter,
            contentDescription = description,
        )
    }
}

/**
 * Шапка с как начать играть и настройками
 *
 */

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            painter = painterResource(id = R.drawable.ic_baseline_question_mark_24),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            description = stringResource(id = R.string.how_to_play_button)
        )
        Text(
            text = stringResource(id = R.string.game_settings),
            fontFamily = primaryFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        IconButton(
            painter = painterResource(id = R.drawable.ic_baseline_settings_24),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            description = stringResource(id = R.string.settings_button)
        )
    }
}

@Composable
fun MenuButton(
    icon: Painter,
    title: String,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BloodRed,
                shape = CircleShape
            )
            .padding(top = 8.dp, end = 10.dp, bottom = 8.dp, start = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
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
    Spacer(
        modifier = Modifier
            .height(10.dp))
}