package com.sadteam.assistantformafia.ui.appsettings

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import java.util.*


data class AppSettingsState(
    val language: String = "en",
    val musicVolume: Float = 1.0f,
    val soundVolume: Float = 1.0f
)

class AppSettingsViewModel: ViewModel() {
    private val _state = mutableStateOf(AppSettingsState())
    val state: State<AppSettingsState> = _state

    sealed class UIEvent {
        data class LanguageChange(val context: Context, val language: String): UIEvent()
        object IncrementMusicVolume: UIEvent()
        object DecrementMusicVolume: UIEvent()
        object IncrementSoundVolume: UIEvent()
        object DecrementSoundVolume: UIEvent()
    }

    fun onEvent (event: UIEvent) {
        when(event) {
            is UIEvent.LanguageChange -> 
                setLanguage(context = event.context, language = "")
        }
    }

    fun setLanguage(context: Context, language: String){
        val locale = Locale(language)
        Locale.setDefault(locale)
        var resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}
