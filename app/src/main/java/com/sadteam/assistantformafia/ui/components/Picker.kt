package com.sadteam.assistantformafia.ui.components

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

@Composable
fun ImagePicker(
    onImagePicked: (ImageBitmap?) -> Unit,
) {
    val context = LocalContext.current

    val launcher = 
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = {uri: Uri? ->
            uri.let {
                if (Build.VERSION.SDK_INT < 28) {
                    onImagePicked(MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it).asImageBitmap())
                } else {
                    val source = ImageDecoder. createSource (context .contentResolver, it!!)
                    onImagePicked(ImageDecoder. decodeBitmap (source).asImageBitmap())
                }
            }
        })

    LaunchedEffect(key1 = Unit, block = {
        launcher.launch("image/*")
    })
}