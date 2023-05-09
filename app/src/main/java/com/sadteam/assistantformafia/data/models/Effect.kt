package com.sadteam.assistantformafia.data.models

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.sadteam.assistantformafia.R

enum class Effect(
    icon: Painter
) {
    HEAL(painterResource(id = R.drawable.heal_effect)),
    KILL(painterResource(id = R.drawable.kill_effect)),
    LOVE(painterResource(id = R.drawable.love_effect)),
    CHECK(painterResource(id = R.drawable.check_effect)),
}