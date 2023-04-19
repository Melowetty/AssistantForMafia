package com.sadteam.assistantformafia.ui.appsettings

import android.content.Context

sealed class AppSettingsEvent {
    data class SetRussian(val context: Context): AppSettingsEvent()
    data class SetEnglish(val context: Context): AppSettingsEvent()
    data class SoundVolumeChange(val value: Float): AppSettingsEvent()
    data class MusicVolumeChange(val value: Float): AppSettingsEvent()
    data class SetSavedLanguage(val context: Context): AppSettingsEvent()
}