package com.sadteam.assistantformafia.models

data class Role(
    val icon: String,
    val slug: String,
    val name: String,
    val description: String,
    val max: Int,
    val possibilities: List<Possibility>,
)
