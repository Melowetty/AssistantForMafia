package com.sadteam.assistantformafia.data

import androidx.room.TypeConverter
import com.sadteam.assistantformafia.data.models.Possibility
import com.sadteam.assistantformafia.data.models.RoleType
import java.util.Locale

class Converters {
    @TypeConverter
    fun fromPossibilities(possibilities: List<Possibility>): String {
        return possibilities.joinToString(separator = ",")
    }

    @TypeConverter
    fun toPossibilities(possibilities: String): List<Possibility> {
        if (possibilities.isEmpty()) {
            return listOf()
        }
        return possibilities.split(",").map {
            Possibility.valueOf(it)
        }
    }

    @TypeConverter
    fun fromTranslatorMap(translator: Map<Locale, String>): String {
        val translatorsList = mutableListOf<String>()
        for (pair in translator) {
            val language = pair.key.language
            val country = pair.key.country
            translatorsList.add("${language}_${country}:${pair.value}")
        }
        return translatorsList.joinToString(separator = "|")

    }

    @TypeConverter
    fun toTranslatorMap(translators: String): Map<Locale, String> {
        if (translators.isEmpty()) return mapOf()
        val translatorsList = translators.split("|")
        val translator = mutableMapOf<Locale, String>()
        for (str in translatorsList) {
            val (locale, value) = str.split(":")
            val (language, country) = locale.split("_")
            translator.put(Locale(language, country), value)
        }
        return translator
    }

    @TypeConverter
    fun fromRoleType(roleType: RoleType): String {
        return roleType.toString()
    }

    @TypeConverter
    fun toRoleType(roleType: String): RoleType {
        return RoleType.valueOf(roleType)
    }
}