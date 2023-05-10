package com.sadteam.assistantformafia.data.models

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.sadteam.assistantformafia.MafiaApplication
import com.sadteam.assistantformafia.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

enum class Effect (
    private val drawableId: Int,
    val priority: Int,
): Comparable<Effect> {
    HEAL(drawableId = R.drawable.heal_effect, 1000),
    KILL(drawableId = R.drawable.kill_effect, 999),
    LOVE(drawableId = R.drawable.love_effect, 10),
    CHECK(drawableId = R.drawable.check_effect, 1);
    fun getDrawable(context: Context): Drawable? {
        return context.getDrawable(drawableId)
    }
}