package com.sadteam.assistantformafia.ui.appsettings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.CustomPopup
import com.sadteam.assistantformafia.ui.components.ExtendedMenuButton
import com.sadteam.assistantformafia.ui.theme.BloodRed
import com.sadteam.assistantformafia.ui.theme.SettingsBackground
import java.util.Locale

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
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 20.dp),
           horizontalArrangement = Arrangement.SpaceBetween,
       ) {
           LanguageButton(
               painter = painterResource(id = R.drawable.russian),
               onClick = onSetRussian,
               isSelected = currentLocale == Locale("ru", "RU"),
           )
           LanguageButton(
               painter = painterResource(id = R.drawable.english),
               onClick = onSetEnglish,
               isSelected = currentLocale == Locale.US,
           )
       }
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
            currentValue = currentLocale.displayLanguage.capitalize(Locale.ROOT),
        )
        SelectLanguagePopup(
            title = title,
            onClose = { isPopupShowed = false },
            isShowed = isPopupShowed,
            modifier = Modifier.wrapContentHeight(),
            onSetRussian = onSetRussian,
            onSetEnglish =  onSetEnglish,
            currentLocale = currentLocale
        )
    }
}