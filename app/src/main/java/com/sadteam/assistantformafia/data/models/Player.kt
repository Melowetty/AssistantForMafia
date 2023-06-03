package com.sadteam.assistantformafia.data.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap

data class Player(
    var icon: MutableState<ImageBitmap?> = mutableStateOf(null),
    var name: MutableState<String> = mutableStateOf(""),
    var role: Role? = null,
    var isSelected: Boolean = false,
    var isLive: Boolean = true,
    var canVote: Boolean = true,
    var canSelectOneself: Boolean = false,
    var previousTarget: Player? = null,
    var effects: MutableList<Effect> = mutableListOf(),
    var voices: MutableState<Int> = mutableStateOf(0),
    var canBeVotedMore: Boolean = true,
) : Cloneable {
    fun addEffect(effect: Effect) {
        if(effects.contains(effect).not()) {
            effects.add(effect)
        }
    }

    fun clearEffects() {
        effects.clear()
    }

    public override fun clone(): Any {
        return Player(
            icon = mutableStateOf(icon.value),
            name = mutableStateOf(name.value),
            role = role,
            isSelected = isSelected,
            isLive = isLive,
            canVote = canVote,
            canSelectOneself = canSelectOneself,
            previousTarget = previousTarget,
            effects = effects.toMutableList(),
            voices = mutableStateOf(voices.value),
            canBeVotedMore = canBeVotedMore,
        )
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Player) {
            return (other.name.value == this.name.value)
                    && (other.icon.value == this.icon.value)
                    && (other.role == this.role)
        } else return false
    }

    override fun toString(): String {
        return "Player(name=${name.value}," +
                " role=${role}," +
                " isSelected=${isSelected}," +
                " isLive=${isLive}," +
                " canVote=${canVote}," +
                " canSelectOneself=${canSelectOneself}," +
                " previousTarget=${previousTarget?.name?.value ?: "None"}," +
                " effects=${effects}," +
                " voices=${voices.value})"
    }
}
