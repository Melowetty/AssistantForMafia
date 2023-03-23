package com.sadteam.assistantformafia.utils

import androidx.room.TypeConverter
import com.sadteam.assistantformafia.data.models.Possibility

class Converters {
        @TypeConverter
        fun fromPossibilities(possibilities: List<Possibility>): String {
            return possibilities.joinToString(separator = ",")
        }

        @TypeConverter
        fun toPossibilities(possibilities: String): List<Possibility> {
            return possibilities.split(",").map {
                Possibility.valueOf(it)
            }
        }
}