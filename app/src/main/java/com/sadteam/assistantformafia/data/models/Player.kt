package com.sadteam.assistantformafia.data.models

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Player(
    var icon: Bitmap? = null
) {
    var name by mutableStateOf("")
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (icon != other.icon) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = icon?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        return result
    }

}
