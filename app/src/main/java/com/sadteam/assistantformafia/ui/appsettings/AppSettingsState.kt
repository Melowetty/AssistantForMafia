package com.sadteam.assistantformafia.ui.appsettings

import java.util.*

data class AppSettingsState(
    val language: Locale = Locale.getDefault(),
    val musicVolume: Float = 1.0f,
    val soundVolume: Float = 1.0f
)