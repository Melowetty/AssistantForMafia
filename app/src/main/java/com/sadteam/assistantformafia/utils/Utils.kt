package com.sadteam.assistantformafia.utils

import com.sadteam.assistantformafia.data.models.Role

class Utils {
    companion object {
        fun getRoleCountLimit(role: Role, currentValue: Int, players: Int, distributedPlayers: Int): Int {
            return Math.min(
                players - distributedPlayers + currentValue,
                role.max
            )
        }
    }
}