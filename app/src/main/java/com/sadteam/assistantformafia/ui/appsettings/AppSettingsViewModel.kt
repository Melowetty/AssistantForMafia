package com.sadteam.assistantformafia.ui.appsettings

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*


data class AppSettingsState(
    val language: Locale = Locale.getDefault(),
    val musicVolume: Float = 1.0f,
    val soundVolume: Float = 1.0f
)

class AppSettingsViewModel: ViewModel() {
    private val _state = mutableStateOf(AppSettingsState())
    val state: State<AppSettingsState> = _state

    sealed class UIEvent {
        data class SetRussian(val context: Context): UIEvent()
        data class SetEnglish(val context: Context): UIEvent()
        data class SoundVolumeChange(val value: Float): UIEvent()
        data class MusicVolumeChange(val value: Float): UIEvent()
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
        }
    }

    private fun setLanguage(context: Context, locale: Locale){
        Locale.setDefault(locale)
        var resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        _state.value = state.value.copy(
            language = locale
        )
    }

}
