package com.sadteam.assistantformafia.data.models

import androidx.compose.ui.graphics.Color
import com.sadteam.assistantformafia.ui.theme.CommonRoleBackgroundColor
import com.sadteam.assistantformafia.ui.theme.CommonRoleTextColor
import com.sadteam.assistantformafia.ui.theme.EnemyRoleBackgroundColor
import com.sadteam.assistantformafia.ui.theme.EnemyRoleTextColor
import com.sadteam.assistantformafia.ui.theme.PeacefulRoleBackgroundColor
import com.sadteam.assistantformafia.ui.theme.PeacefulRoleTextColor

enum class RoleType(val backgroundColor: Color, val textColor: Color) {
    ENEMY(EnemyRoleBackgroundColor, EnemyRoleTextColor),
    PEACEFUL(PeacefulRoleBackgroundColor, PeacefulRoleTextColor),
    COMMON(CommonRoleBackgroundColor, CommonRoleTextColor),
}