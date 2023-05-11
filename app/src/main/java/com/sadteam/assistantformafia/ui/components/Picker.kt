package com.sadteam.assistantformafia.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.theme.DarkBlue
import com.sadteam.assistantformafia.ui.theme.Gray
import com.sadteam.assistantformafia.ui.theme.LightGray
import com.sadteam.assistantformafia.ui.theme.secondFontFamily

@Composable
fun ImagePickerFromGallery(
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

@Composable
fun ImagePickerFromCamera(
    onImagePicked: (ImageBitmap?) -> Unit,
) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            onImagePicked(it?.asImageBitmap())
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            launcher.launch()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }


    LaunchedEffect(key1 = Unit, block = {
        val permissionCheckResult =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            launcher.launch()
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    })
}

@Composable
fun PlayersCountValuePicker(
    value: Int,
    min: Int = 0,
    max: Int = Int.MAX_VALUE,
    onIncreasing: () -> Unit,
    onDecreasing: () -> Unit,
) {
    ValuePicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        value = value,
        min = min,
        max = max,
        onIncreasing = onIncreasing,
        onDecreasing = onDecreasing,
    )
}

@Composable
fun ValuePicker(
    modifier: Modifier = Modifier,
    value: Int,
    min: Int = 0,
    max: Int = Int.MAX_VALUE,
    onIncreasing: () -> Unit,
    onDecreasing: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            painter = painterResource(id = R.drawable.baseline_remove_24),
            size = 33.dp,
            backgroundColor = if (value > min) DarkBlue else Gray,
            iconColor = if (value > min) Color.White else LightGray,
            disabled = value == min,
            description = "remove",
            onClick = onDecreasing,
        )
        Text(
            text = "$value",
            color = Color.White,
            fontFamily = secondFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        IconButton(
            painter = painterResource(id = R.drawable.baseline_add_24),
            size = 33.dp,
            backgroundColor = if (value < max) DarkBlue else Gray,
            iconColor = if (value < max) Color.White else LightGray,
            disabled = value == max,
            description = "add",
            onClick = onIncreasing,
        )
    }
}