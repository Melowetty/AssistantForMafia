package com.sadteam.assistantformafia.data.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import java.util.*

data class Role(
    val icon: ImageBitmap,
    val playerIcon: ImageBitmap?,
    val name: Map<Locale, String>,
    val defaultName: String,
    val description: Map<Locale, String>,
    val defaultDescription: String,
    val defaultWinMessage: String = "",
    val winMessage: Map<Locale, String> = mapOf(),
    val min: Int,
    val max: Int = Int.MAX_VALUE,
    val roleType: RoleType,
    val effect: Effect? = null,
    val canSelectOneself: Boolean = false,
    val canSelectSameTarget: Boolean = true,
    val canBeSelectedMore: MutableState<Boolean> = mutableStateOf(true),
    val selectedCount: MutableState<Int> = mutableIntStateOf(min),
    val priority: Int,
) : Cloneable {
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

    public override fun clone(): Any {
        return Role(
            icon = icon,
            playerIcon = playerIcon,
            name = name,
            defaultName = defaultName,
            description = description,
            defaultDescription = defaultDescription,
            defaultWinMessage = defaultWinMessage,
            winMessage = winMessage,
            min = min,
            max = max,
            roleType = roleType,
            effect = effect,
            canSelectOneself = canSelectOneself,
            canSelectSameTarget = canSelectSameTarget,
            canBeSelectedMore = mutableStateOf(canBeSelectedMore.value),
            selectedCount = mutableIntStateOf(selectedCount.value),
            priority = priority,
        )
    }

    override fun toString(): String {
        return "Role(name=${defaultName}," +
                " roleType=${roleType}," +
                " min=${min}," +
                " max=${max}," +
                " effect=${effect}," +
                " canSelectOneself=${canSelectOneself}," +
                " canSelectSameTarget=${canSelectSameTarget}," +
                " winMessage=${winMessage}," +
                " selectedCount=${selectedCount}," +
                " canBeSelectedMore=${canBeSelectedMore})"

    }
}