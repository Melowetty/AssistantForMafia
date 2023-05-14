package com.sadteam.assistantformafia.data.models

import com.sadteam.assistantformafia.R

enum class Effect (
    val drawableId: Int,
    val priority: Int,
) {
    HEAL(drawableId = R.drawable.heal_effect, 1000),
    KILL(drawableId = R.drawable.kill_effect, 999),
    LOVE(drawableId = R.drawable.love_effect, 99),
    CHECK(drawableId = R.drawable.check_effect, 1),
    KICK(drawableId = R.drawable.kick_effect, 10000);
}