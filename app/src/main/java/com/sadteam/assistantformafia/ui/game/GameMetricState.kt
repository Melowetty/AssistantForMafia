package com.sadteam.assistantformafia.ui.game

import java.util.*

data class GameMetricState(
    val gameId: UUID? = null,
    val startTime: Date? = null,
    val endTime: Date? = null,
    val duration: Long? = null,
    val players: Int = 0,
    val rounds: Int = 0,
)