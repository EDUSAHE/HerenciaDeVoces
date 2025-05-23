package com.example.herenciadevoces.ui.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.herenciadevoces.R
import com.example.herenciadevoces.domain.LanguageWordDate.model.LanguageWordData
import com.example.herenciadevoces.domain.LanguageWordDate.model.LanguageWordDataAndLV
import com.example.herenciadevoces.domain.SpanishWordData.model.SpanishWordData
import com.example.herenciadevoces.ui.theme.Orange
import com.example.herenciadevoces.ui.theme.YellowBackGround
import com.example.herenciadevoces.ui.viewmodels.WordSoundsViewModel
import java.io.InputStream
import com.example.herenciadevoces.ui.components.Header
import com.example.herenciadevoces.ui.interaction.LanguageANDLanguageVariantState

@Composable
fun WordSoundsScreen(viewModel: WordSoundsViewModel = hiltViewModel()) {
    val actualItem by viewModel.actualItem.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val semanticFieldName by viewModel.semanticFieldName.collectAsState()

    if (isLoading) {
        CircularProgressIndicator() // Show loading indicator
    } else {
        actualItem?.let { wordData ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = YellowBackGround)
            ) {
                Header(title = semanticFieldName.uppercase())//Header(title = ${wordData.})
                WordImage(wordData)
                Word(wordData)
                ItemsLanguageVariants(wordData.lWD)

                /*
                wordData.LWD.forEach { variantData ->
                    VariantSoundButton(variantData)
                }
                */

                Spacer(modifier = Modifier.weight(1f))
                NavButtons(viewModel)
            }
        } ?: Text("No hay datos disponibles")

    }
}

@Composable
fun ItemsLanguageVariants(
    lwd: MutableList<LanguageWordDataAndLV>
) {
    LazyColumn {
        items(lwd) { languageWordData ->
            VariantSoundButton(languageWordData)
        }
    }
}

@Composable
fun WordImage(wordData: SpanishWordData) {
    val context = LocalContext.current
    val currentPath by rememberUpdatedState(newValue = wordData.pathImage)

    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    LaunchedEffect(key1 = currentPath) {
        bitmap = loadBitmapFromAssets(context, currentPath)
    }
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(1f),
        // .border(3.dp, color = Orange, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                //painter = painterResource(id = R.drawable.campo),
                contentDescription = "Imágen de ${wordData.spanishWord}",
                modifier = Modifier
                    .size(200.dp)
                    .border(3.dp, color = Orange, shape = RoundedCornerShape(20.dp))
                    .scale(0.9f)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
    }

}

@Composable
fun Word(wordData: SpanishWordData) {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically, // Alinea verticalmente
        horizontalArrangement = Arrangement.Center // Centra horizontalmente

    ) {
        Text(
            text = wordData.spanishWord,
            modifier = Modifier.padding(end = 12.dp),
            textAlign = TextAlign.Center,
            fontSize = 35.sp
        )

        IconButton(
            onClick = {
                mediaPlayer?.release() // Libera recursos si ya está reproduciendo
                mediaPlayer = MediaPlayer().apply {
                    val assetFileDescriptor = context.assets.openFd(wordData.pathAudio)
                    setDataSource(
                        assetFileDescriptor.fileDescriptor,
                        assetFileDescriptor.startOffset,
                        assetFileDescriptor.length
                    )
                    prepare()
                    start()
                }
            }
        ) {
            //Text(text = wordData.spanishWord, modifier = Modifier.padding(end = 4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_volumeup),
                contentDescription = "Reproducir Sonido",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }
    }

}

@Composable
fun VariantSoundButton(
    languageWordData: LanguageWordDataAndLV
) {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    val currentPath by rememberUpdatedState(newValue = languageWordData.pathImage)

    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    LaunchedEffect(key1 = currentPath) {
        bitmap = loadBitmapFromAssets(context, currentPath)
    }

    Row(
        modifier = Modifier.padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        bitmap?.let{
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = languageWordData.languageName,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(text = languageWordData.languageWord, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Text(text = "${languageWordData.languageName}, ${languageWordData.variantName}", fontSize = 12.sp, modifier = Modifier.width(215.dp))
        }

        IconButton(
            onClick = {
                mediaPlayer?.release() // Libera recursos si ya está reproduciendo
                mediaPlayer = MediaPlayer().apply {
                    val assetFileDescriptor =
                        context.assets.openFd(languageWordData.pathAudio)
                    setDataSource(
                        assetFileDescriptor.fileDescriptor,
                        assetFileDescriptor.startOffset,
                        assetFileDescriptor.length
                    )
                    prepare()
                    start()
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_volumeup),
                contentDescription = "Reproducir Sonido",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }


    }
}

@Composable
fun NavButtons(viewModel: WordSoundsViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(1f).padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedButton(
            onClick = {
                viewModel.previousItem()
            },
            colors = ButtonColors(
                containerColor = Orange,
                contentColor = Orange,
                disabledContentColor = Orange,
                disabledContainerColor = Orange
            ),
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.left),
                "Anterior",
                modifier = Modifier.size(35.dp),
                tint = Color.White
            )
        }
        ElevatedButton(
            onClick = {
                viewModel.nextItem()
            },
            colors = ButtonColors(
                containerColor = Orange,
                contentColor = Orange,
                disabledContentColor = Orange,
                disabledContainerColor = Orange
            ),
            modifier = Modifier.padding(end = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.right),
                "Siguiente",
                modifier = Modifier.size(35.dp),
                tint = Color.White
            )
        }
    }
}

fun loadBitmapFromAssets(context: Context, assetPath: String): Bitmap? {
    return try {
        context.assets.open(assetPath).use { inputStream: InputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
