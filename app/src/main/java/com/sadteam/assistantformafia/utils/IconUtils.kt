package com.sadteam.assistantformafia.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

class IconUtils {
    companion object {

        /**
         * Конвертация закодированного изображения без указания типа из Base64 в ImageBitmap
         *
         * @return раскодированное изображение
         */
        fun String.toImageBitmap(): ImageBitmap {
            val decodeBitmap = this.toBitmap()
            return decodeBitmap.asImageBitmap()
        }

        /**
         * Конвертация закодированного изображения без указания типа из Base64 в Bitmap
         *
         * @return раскодированный текст в bitmap
         */
        fun String.toBitmap(): Bitmap {
            Base64.decode(this, Base64.DEFAULT).apply {
                return BitmapFactory.decodeByteArray(this, 0, size)
            }
        }
    }
}