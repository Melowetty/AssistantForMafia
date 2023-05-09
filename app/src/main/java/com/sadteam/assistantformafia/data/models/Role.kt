package com.sadteam.assistantformafia.data.models

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Locale

@Entity(tableName = "roles")
data class Role(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val icon: String,
    val playerIcon: String,
    val name: Map<Locale, String>,
    val defaultName: String,
    val description: Map<Locale, String>,
    val defaultDescription: String,
    val min: Int,
    val max: Int = Int.MAX_VALUE,
    val possibilities: List<Possibility>,
    val roleType: RoleType,
    val effect: Effect? = null,
) {
    fun getTranslatedName(): String {
        var translatedName = name[Locale.getDefault()]
        if(translatedName.isNullOrEmpty()) {
            translatedName = defaultName
        }
        return translatedName
    }

    fun getTranslatedDescription(): String {
        var translatedDescription = description[Locale.getDefault()]
        if(translatedDescription.isNullOrEmpty()) {
            translatedDescription = defaultDescription
        }
        return translatedDescription
    }

    fun getBackgroundColor(): Color {
        return roleType.backgroundColor
    }

    fun getTextColor(): Color {
        return roleType.textColor
    }
}
