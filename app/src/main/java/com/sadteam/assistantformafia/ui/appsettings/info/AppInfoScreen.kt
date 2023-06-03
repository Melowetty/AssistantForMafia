package com.sadteam.assistantformafia.ui.appsettings.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.BuildConfig
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily

@Composable
fun AppInfoScreen(
    navController: NavController
) {
    MainLayout(
        navController = navController,
        isVisibleSettingsButton = false,
        title = stringResource(id = R.string.about_app)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(150.dp),
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = primaryFontFamily,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    text = stringResource(id = R.string.app_name)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = primaryFontFamily,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    color = Color.White,
                    text = "${stringResource(id = R.string.version)} " +
                            "${BuildConfig.VERSION_NAME}, " +
                            "${stringResource(id = R.string.build)} " +
                            "${BuildConfig.VERSION_CODE}"
                )
            }
        }
    }
}