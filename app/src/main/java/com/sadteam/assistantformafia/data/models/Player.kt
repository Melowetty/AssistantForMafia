package com.sadteam.assistantformafia.data.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap

data class Player(
    var icon: MutableState<ImageBitmap?> = mutableStateOf(null),
    var name: MutableState<String> = mutableStateOf(""),
    var role: Role? = null,
    var isSelected: Boolean = false,
    var isLive: Boolean = true,
    var canVote: Boolean = true,
    var effects: MutableList<Effect> = mutableListOf(),
    var voices: MutableState<Int> = mutableStateOf(0),
) {
    fun addEffect(effect: Effect) {
        if(effects.contains(effect).not()) {
            effects.add(effect)
        }
    }

    fun clearEffects() {
        effects.clear()
    }
}
