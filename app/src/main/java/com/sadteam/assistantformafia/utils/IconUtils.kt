package com.sadteam.assistantformafia.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.sadteam.assistantformafia.R

class IconUtils {
    companion object {

        fun String.toRoleIcon(context: Context): ImageBitmap {
            return try {
                val imageBytes = Base64.decode(this, 0)
                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
            } catch (exception: java.lang.Exception) {
                exception.printStackTrace()
                Utils.getBitmapFromImage(context, R.drawable.baseline_help_24).asImageBitmap()
            }
        }

        /**
         * Input base64 encoded image (not svg!!!) string
         *
         * @return ImageBitmap encoded image
         */
        fun String.toImageBitmap(): ImageBitmap? {
            return try {
                val imageBytes = Base64.decode(this, 0)
                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
            } catch (exception: java.lang.Exception) {
                null
            }
        }
    }
}