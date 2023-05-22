package com.sadteam.assistantformafia.data.models

import androidx.compose.ui.graphics.Color
import com.sadteam.assistantformafia.ui.theme.*

enum class RoleType(val backgroundColor: Color, val textColor: Color) {
    ENEMY(EnemyRoleBackgroundColor, EnemyRoleTextColor),
    PEACEFUL(PeacefulRoleBackgroundColor, PeacefulRoleTextColor),
    COMMON(CommonRoleBackgroundColor, CommonRoleTextColor),
}