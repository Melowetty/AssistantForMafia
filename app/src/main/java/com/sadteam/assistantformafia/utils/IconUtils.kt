package com.sadteam.assistantformafia.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import com.sadteam.assistantformafia.R

class IconUtils {
    companion object {

        @Composable
        fun String.toRoleIcon(): Any {
            return try {
                val imageBytes = Base64.decode(this, 0)
                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
            } catch (expection: java.lang.Exception) {
                expection.printStackTrace()
                painterResource(id = R.drawable.baseline_help_24)
            }
        }
    }
}