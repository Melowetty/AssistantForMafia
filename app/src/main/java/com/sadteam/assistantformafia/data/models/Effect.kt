package com.sadteam.assistantformafia.data.models

import com.sadteam.assistantformafia.R

enum class Effect (
    val drawableId: Int,
    val priority: Int,
): Comparable<Effect> {
    HEAL(drawableId = R.drawable.heal_effect, 1000),
    KILL(drawableId = R.drawable.kill_effect, 999),
    LOVE(drawableId = R.drawable.love_effect, 10),
    CHECK(drawableId = R.drawable.check_effect, 1);
}