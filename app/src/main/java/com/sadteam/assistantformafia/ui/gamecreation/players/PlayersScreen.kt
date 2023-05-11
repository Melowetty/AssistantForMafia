package com.sadteam.assistantformafia.ui.gamecreation.players

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.data.models.Player
import com.sadteam.assistantformafia.ui.components.*
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationEvent
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationState
import com.sadteam.assistantformafia.ui.theme.DarkBackground
import com.sadteam.assistantformafia.ui.theme.primaryFontFamily
import com.sadteam.assistantformafia.utils.MIN_PLAYERS_COUNT
import com.sadteam.assistantformafia.utils.Utils

@Composable
fun PlayersScreen(
    navController: NavController,
    state: GameCreationState,
    onEvent: (GameCreationEvent) -> Unit,
) {
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.players_count),
    ) {
        PlayersCountValuePicker(
            onIncreasing = { onEvent(GameCreationEvent.IncrementPlayers) },
            onDecreasing = { onEvent(GameCreationEvent.DecrementPlayers) },
            value = state.players.size,
            min = MIN_PLAYERS_COUNT,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            content = {
                itemsIndexed(state.players) {index: Int, item: Player ->
                    var isPhotoSelecting by remember {
                        mutableStateOf(false)
                    }
                    Card(
                        content = {
                            PlayerNameKeyboard(
                                modifier = Modifier.width(160.dp),
                                value = item.name.value,
                                onValueChange = { newText ->
                                    onEvent(
                                        GameCreationEvent.SetPlayerName(index, newText)
                                    )
                                },
                                placeholder = stringResource(id = R.string.enter_name),
                            )
                        },
                        mainIcon = item.icon.value?: Utils.getBitmapFromImage(LocalContext.current, R.drawable.add_a_photo).asImageBitmap(),
                        mainIconModifier = if(item.icon.value != null) Modifier else Modifier.padding(8.dp),
                        secondIcon = painterResource(id = R.drawable.delete),
                        onMainIconClick = {
                            isPhotoSelecting = true
                        },
                        onSecondIconClick = {
                            onEvent(
                                GameCreationEvent.DeletePlayer(index)
                            )
                        }
                    )
                    if (isPhotoSelecting) {
                        ImageUploadPopup(onImageUpload = {
                            onEvent(
                                GameCreationEvent.SetPlayerImage(index, it)
                            )
                            isPhotoSelecting = false
                        })
                    }
                }
            })
    }
}

@Composable
fun ImageUploadPopup(
    onImageUpload: (ImageBitmap?) -> Unit,
) {
    var isTakingPicture by remember {
        mutableStateOf(false)
    }
    var isUploadingFromDevice by remember {
        mutableStateOf(false)
    }
    Popup(
        alignment = Alignment.Center,
        properties = PopupProperties(focusable = true),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = DarkBackground,
                )
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .wrapContentHeight()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.photo),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = primaryFontFamily,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clickable { isTakingPicture = true },
                    text = stringResource(id = R.string.take_photo),
                    fontSize = 16.sp,
                    fontFamily = primaryFontFamily,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clickable { isUploadingFromDevice = true },
                    text = stringResource(id = R.string.upload_from_device),
                    fontSize = 16.sp,
                    fontFamily = primaryFontFamily,
                    color = Color.Black
                )
            }
        }
        if (isTakingPicture) {
            ImagePickerFromCamera(onImagePicked = onImageUpload)
        }
        if (isUploadingFromDevice) {
            ImagePickerFromGallery(onImagePicked = onImageUpload)
        }
    }
}