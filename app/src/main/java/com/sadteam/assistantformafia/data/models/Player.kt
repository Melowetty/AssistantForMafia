package com.sadteam.assistantformafia.data.models

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Player(
    var icon: Bitmap? = null,
    var name: MutableState<String> = mutableStateOf("")
)
