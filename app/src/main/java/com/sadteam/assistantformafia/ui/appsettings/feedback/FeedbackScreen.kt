package com.sadteam.assistantformafia.ui.appsettings.feedback

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.ExtendedMenuButton
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.components.bounceClick
import com.sadteam.assistantformafia.ui.theme.SettingsBackground

@Composable
fun FeedbackScreen(
    navController: NavController
) {
    val context = LocalContext.current
    MainLayout(
        navController = navController,
        isVisibleSettingsButton = false,
        title = stringResource(id = R.string.feedback)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ExtendedMenuButton(
                modifier = Modifier
                    .bounceClick()
                    .background(
                        color = SettingsBackground,
                        shape = RoundedCornerShape(10.dp)
                    ),
                title = stringResource(id = R.string.contact_us),
                onClick = {
                    sendMail(
                        context = context,
                        to = "melowetty@mail.ru"
                    )
                },
                icon = painterResource(id = R.drawable.baseline_email_24),
            )
            ExtendedMenuButton(
                modifier = Modifier
                    .bounceClick()
                    .background(
                        color = SettingsBackground,
                        shape = RoundedCornerShape(10.dp)
                    ),
                title = stringResource(id = R.string.write_us),
                onClick = {
                    openTelegram(context)
                },
                icon = painterResource(id = R.drawable.telegram),
            )
        }
    }
}

fun openTelegram(context: Context) {
    var intent: Intent? = null
    try {
        try {
            context.packageManager.getPackageInfo("org.telegram.messenger", 0)
        } catch (e : Exception) {
            context.packageManager.getPackageInfo("org.thunderdog.challegram", 0)
        }
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=MafiaAssistant"))
    } catch (e : Exception) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.t.me/MafiaAssistant"))
    }
    context.startActivity(intent)
}

fun sendMail(context: Context, to: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        context.startActivity(intent)
    } catch (ignored: Exception) {

    }
}