package com.sadteam.assistantformafia.ui.appsettings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.CustomPopup
import com.sadteam.assistantformafia.ui.components.ExtendedMenuButton
import com.sadteam.assistantformafia.ui.components.SmallButton
import com.sadteam.assistantformafia.ui.tags.SelectCountTags
import com.sadteam.assistantformafia.ui.theme.*
import java.util.*

@Composable
fun LanguageButton(
    painter: Painter,
    description: String = "",
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    var modifier: Modifier = Modifier
    if (isSelected) {
        modifier = modifier
            .border(2.dp, BloodRed, shape = RoundedCornerShape(20))
    }
    Box(
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(20)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (!isSelected) onClick()
                }
            )
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = description
        )
    }
}

@Composable
fun SelectLanguagePopup(
    modifier: Modifier = Modifier,
    title: String,
    isShowed: Boolean = false,
    onClose: () -> Unit,
    onSetRussian: () -> Unit,
    onSetEnglish: () -> Unit,
    currentLocale: Locale
) {
   CustomPopup(
       modifier = modifier,
       title = title,
       onClose = onClose,
       isShowed = isShowed,
   ) {
       LanguageButton(
           painter = painterResource(id = R.drawable.russian),
           onClick = onSetRussian,
           isSelected = currentLocale == Locale("ru", "RU"),
       )
       LanguageButton(
           painter = painterResource(id = R.drawable.english),
           onClick = onSetEnglish,
           isSelected = currentLocale == Locale.ENGLISH,
       )
   }
}

@Composable
fun SelectLanguage(
    modifier: Modifier = Modifier,
    title: String,
    onSetRussian: () -> Unit,
    onSetEnglish: () -> Unit,
    currentLocale: Locale
) {
    var isPopupShowed by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Box(modifier = modifier) {
        ExtendedMenuButton(
            modifier = Modifier
                .background(
                    color = SettingsBackground,
                    shape = RoundedCornerShape(20.dp)
                ),
            icon = painterResource(id = R.drawable.baseline_language_24),
            title = stringResource(id = R.string.language),
            onClick = { isPopupShowed = true },
            currentValue = currentLocale.displayLanguage,
        )
        SelectLanguagePopup(
            title = title,
            onClose = { isPopupShowed = false },
            isShowed = isPopupShowed,
            onSetRussian = onSetRussian,
            onSetEnglish =  onSetEnglish,
            currentLocale = currentLocale
        )
    }
}