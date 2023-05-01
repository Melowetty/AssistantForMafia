package com.sadteam.assistantformafia.utils

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.utils.IconUtils.Companion.toRoleIcon

class IconUtils {
    companion object {

        @Composable
        fun String.toRoleIcon(): Any {
            return try {
                val imageBytes = Base64.decode(this, 0)
                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
            } catch (exception: java.lang.Exception) {
                exception.printStackTrace()
                painterResource(id = R.drawable.baseline_help_24)
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