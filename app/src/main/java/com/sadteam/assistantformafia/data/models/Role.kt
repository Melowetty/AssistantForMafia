package com.sadteam.assistantformafia.data.models

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "roles")
data class Role(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val icon: String,
    val playerIcon: String,
    val name: Map<Locale, String>,
    val defaultName: String,
    val description: Map<Locale, String>,
    val defaultDescription: String,
    val defaultWinMessage: String = "",
    val winMessage: Map<Locale, String> = mapOf(),
    val min: Int,
    val max: Int = Int.MAX_VALUE,
    val possibilities: List<Possibility>,
    val roleType: RoleType,
    val effect: Effect? = null,
    val canSelectOneself: Boolean = false,
    val canSelectSameTarget: Boolean = true,
) {
    fun getTranslatedName(): String {
        var translatedName = name[Locale.getDefault()]
        if(translatedName.isNullOrEmpty()) {
            translatedName = defaultName
        }
        return translatedName
    }

    fun getTranslatedWinMessage(): String {
        var translatedWinMessage = winMessage[Locale.getDefault()]
        if(translatedWinMessage.isNullOrEmpty()) {
            translatedWinMessage = defaultWinMessage
        }
        return translatedWinMessage
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

    override fun toString(): String {
        return "Role(name=${defaultName}," +
                " roleType=${roleType}," +
                " possibilities=${possibilities}," +
                " min=${min}," +
                " max=${max}," +
                " effect=${effect}," +
                " canSelectOneself=${canSelectOneself}," +
                " canSelectSameTarget=${canSelectSameTarget}, " +
                " winMessage=${winMessage})"

    }
}
