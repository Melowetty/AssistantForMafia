package com.sadteam.assistantformafia.data.models

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Player(
    var icon: Bitmap? = null,
    var name: MutableState<String> = mutableStateOf("")
)
