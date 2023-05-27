package com.sadteam.assistantformafia.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.sadteam.assistantformafia.data.models.Player

class Utils {
    companion object {
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

        data class ColoredMessage(val colored: String, val message: String)

        fun getColoredMessage(str: String): ColoredMessage {
            val regex = Regex("\\[(.*?)\\]")
            val matches = regex.find(str)
            return if (matches != null) {
                ColoredMessage(colored = matches.groupValues.get(1),
                    message = regex.replaceFirst(str, "")
                )
            } else {
                ColoredMessage(colored = "", message = str)
            }
        }

    }
}