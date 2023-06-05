package com.sadteam.assistantformafia.data.models.entities

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sadteam.assistantformafia.data.models.Effect
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.data.models.RoleType
import com.sadteam.assistantformafia.utils.IconUtils.Companion.toImageBitmap
import com.sadteam.assistantformafia.utils.IconUtils.Companion.toRoleIcon
import java.util.*

@Entity(tableName = "roles")
data class DbRole(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val icon: String,
    val playerIcon: String,
    val name: Map<Locale, String>,
    val defaultName: String,
    val description: Map<Locale, String>,
    val defaultDescription: String,
    val defaultWinMessage: String = "",
    val winMessage: Map<Locale, String> = mapOf(),
    val min: Int,
    val max: Int = Int.MAX_VALUE,
    val roleType: RoleType,
    val effect: Effect? = null,
    val canSelectOneself: Boolean = false,
    val canSelectSameTarget: Boolean = true,
    val priority: Int,
) {
    companion object {
        fun DbRole.toRole(context: Context): Role {
            val iconImageBitmap = icon.toRoleIcon(context)
            val playerIconImageBitmap = playerIcon.toImageBitmap()
            return Role(
                icon = iconImageBitmap,
                playerIcon = playerIconImageBitmap,
                name = name,
                defaultName = defaultName,
                description = description,
                defaultDescription = defaultDescription,
                defaultWinMessage = defaultWinMessage,
                winMessage = winMessage,
                min = min,
                max = max,
                roleType = roleType,
                effect = effect,
                canSelectOneself = canSelectOneself,
                canSelectSameTarget = canSelectSameTarget,
                priority = priority,
            )
        }
    }
}
