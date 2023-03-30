package com.sadteam.assistantformafia.ui.appsettings

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject


data class AppSettingsState(
    val language: Locale = Locale.getDefault(),
    val musicVolume: Float = 1.0f,
    val soundVolume: Float = 1.0f
)

@HiltViewModel
class AppSettingsViewModel @Inject constructor(
    private val preferences: SharedPreferences
    ): ViewModel() {
    private val _state = mutableStateOf(AppSettingsState())
    val state: State<AppSettingsState> = _state

    sealed class UIEvent {
        data class SetRussian(val context: Context): UIEvent()
        data class SetEnglish(val context: Context): UIEvent()
        data class SoundVolumeChange(val value: Float): UIEvent()
        data class MusicVolumeChange(val value: Float): UIEvent()
        data class SetSavedLanguage(val context: Context): UIEvent()
    }

    fun onEvent (event: UIEvent) {
        when(event) {
            is UIEvent.SetRussian ->
                setLanguage(context = event.context, locale = Locale("ru", "RU"))
            is UIEvent.SetEnglish ->
                setLanguage(context = event.context, locale = Locale.US)
            is UIEvent.SoundVolumeChange ->
                _state.value = state.value.copy(
                    soundVolume = event.value
                )
            is UIEvent.MusicVolumeChange ->
                _state.value = state.value.copy(
                    musicVolume = event.value
                )
            is UIEvent.SetSavedLanguage ->
                setSavedLanguage(context = event.context)
        }
    }

    private fun setLanguage(context: Context, locale: Locale, save: Boolean = true){
        Locale.setDefault(locale)
        var resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        _state.value = state.value.copy(
            language = locale
        )
        if (save) with (preferences.edit()) {
            putString("app_language", "${locale.language}_${locale.country}")
            apply()
        }
    }

    private fun setSavedLanguage(context: Context) {
        val defaultValue = "${Locale.getDefault().language}_{Locale.getDefault().country}"
        val appLanguage = preferences.getString("app_language", defaultValue)!!.split("_")
        val language = appLanguage[0]
        val country = appLanguage[1]
        setLanguage(context, Locale(language, country), false)
    }

}
