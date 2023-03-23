package com.sadteam.assistantformafia.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roles")
data class Role(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val icon: String,
    val name: String,
    val description: String,
    val max: Int,
    val possibilities: List<Possibility>,
)
