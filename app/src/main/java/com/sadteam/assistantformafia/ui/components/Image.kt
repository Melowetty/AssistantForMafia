package com.sadteam.assistantformafia.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sadteam.assistantformafia.data.models.Effect

@Composable
fun EffectIcon(
    effect: Effect
) {
    val context = LocalContext.current
    Icon(
        modifier = Modifier
            .size(28.dp),
        tint = Color.White,
        painter = painterResource(effect.drawableId),
        contentDescription = "Effect: ${effect.name}"
    )
}