package com.sadteam.assistantformafia.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.data.models.Role

class Utils {
    companion object {
        fun getRoleCountLimit(role: Role, currentValue: Int, players: Int, distributedPlayers: Int): Int {
            return Math.min(
                players - distributedPlayers + currentValue,
                role.max
            )
        }

        fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {

            val db = ContextCompat.getDrawable(context, drawable)

            val bit = Bitmap.createBitmap(
                db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(bit)

            db.setBounds(0, 0, canvas.width, canvas.height)

            db.draw(canvas)

            return bit
        }

        fun findIndexPlayerByName(players: List<Player>, name: String): Int {
            for ((index, player) in players.withIndex()) {
                if (player.name.value == name) return index
            }
            return -1
        }

    }
}