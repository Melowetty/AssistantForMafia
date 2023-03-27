package com.sadteam.assistantformafia.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

class IconUtils {
    companion object {

        fun String.toImageBitmap(): ImageBitmap {
            val imageBytes = Base64.decode(this, 0)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
        }
    }
}