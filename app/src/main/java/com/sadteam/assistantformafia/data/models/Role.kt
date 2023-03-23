package com.sadteam.assistantformafia.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roles")
data class Role(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val icon: String,
    val name: String,
    val description: String,
    val min: Int,
    val max: Int = Int.MAX_VALUE,
    val possibilities: List<Possibility>,
)
