package com.example.herenciadevoces.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.herenciadevoces.ui.components.Header
import com.example.herenciadevoces.ui.theme.Orange
import com.example.herenciadevoces.ui.viewmodels.LanguageSelectionViewModel

/*
data class Language(
    val languageName: String,
    val location: String,
    val region: String,
    val imageResId: Int
)



val languages = listOf(
    Language("Mixteco", "Coicoyán de las Flores, Juxtlahuaca.", "Mixteca Baja", R.drawable.mixteco),
    Language("Zapoteco", "San Agustín Loxicha.", "Costa Centro", R.drawable.zapoteco),
    Language("Otro Idioma 1", "Ubicación 1", "Región 1", R.drawable.idiomaprueba1),
    Language("Otro Idioma 2", "Ubicación 2", "Región 2", R.drawable.idiomaprueba2),
)

 */

data class SelectedVariantData(
    val idLanguageVariant: Int,
    val variantName: String,
    val pathImage: String
)




//@Preview(showSystemUi = true)
@Composable
fun LanguageSelectionScreen(
    viewModel: LanguageSelectionViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToSFS: (List<Int>) -> Unit) {

    val data = viewModel.state.value

    //val selectedImageResId = remember { mutableStateOf<Int?>(null) } // Estado para la imagen seleccionada
    val selectedLanguageVariantsIds =
        remember { mutableStateListOf<Int>() } // Estado para la lista de imágenes seleccionadas
    val selectedLanguageVariants =
        remember { mutableStateListOf<SelectedVariantData>() }
    Scaffold(
        topBar = {
            Header(title = "IDIOMAS DE MÉXICO!")
        },
        floatingActionButton = {
            ElevatedButton(
                onClick = { navigateToSFS(selectedLanguageVariantsIds) }, //onClick = { navigateToSFS(languageVariant.idLanguageVariant) },
                enabled = selectedLanguageVariantsIds.isNotEmpty(),
                modifier = Modifier.size(65.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonColors(
                    contentColor = Orange,
                    containerColor = Orange,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward, // Icono de flecha derecha
                    contentDescription = "Flecha derecha",
                    tint = Color.White, // Color del icono
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Ya Seleccionados
            Selected(
                selectedLanguageVariants = selectedLanguageVariants
            ) // Pasamos el estado
            LazyColumn {
                item {
                    Box(
                        Modifier
                            .padding(start = 20.dp)
                            .fillMaxWidth()
                            .height(30.dp)
                    ) {
                        Text(text = "Seleccione los idiomas que desee:", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                }
                items(data.languageVariants) { languageVariant ->
                    LanguageItem(
                        languageName = languageVariant.languageName,
                        variantName = languageVariant.variantName,
                        variantRegionName = languageVariant.variantRegionName,
                        languagePathImage = languageVariant.pathImage,
                        idLanguageVariant = languageVariant.idLanguageVariant,
                        onItemClick = { idLanguageVariant ->
                            if (selectedLanguageVariantsIds.contains(idLanguageVariant)) {
                                selectedLanguageVariantsIds.remove(idLanguageVariant)
                                selectedLanguageVariants.removeIf { it.idLanguageVariant == idLanguageVariant }
                            } else {
                                selectedLanguageVariantsIds.add(idLanguageVariant)
                                selectedLanguageVariants.add(
                                    SelectedVariantData(
                                        idLanguageVariant = idLanguageVariant,
                                        variantName = languageVariant.variantName,
                                        pathImage = languageVariant.pathImage
                                    ))
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun Selected(selectedLanguageVariants: SnapshotStateList<SelectedVariantData>) {
    Box(
        Modifier
            .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 12.dp)
            .fillMaxWidth()
            .height(180.dp)
            //.background(Color.Red),
    ) {
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                //.background(Color.Yellow)
            ) {
                Text(
                    text = "SELECCIONADOS",
                    fontSize = 20.sp
                )
            }
            Box(
                Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .padding(5.dp),
                //.background(Color.LightGray),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(start = 14.dp)
                ) {
                    selectedLanguageVariants.forEach { languageVariants ->
                        LanguageImage(

                            pathImage = languageVariants.pathImage,
                            variantName = languageVariants.variantName,
                            contentDescription = "Imagen seleccionada"


                        )
                        Spacer(modifier = Modifier.width(14.dp)) // Espacio entre imágenes
                    }
                }
            }
        }
    }
}


// Definir el estilo que tendrá el Card Language
@Composable
fun LanguageItem(
    languageName: String,
    variantName: String,
    variantRegionName: String,
    languagePathImage:String,
    idLanguageVariant: Int,
    onItemClick: (Int) -> Unit // Recibimos la función lambda
) {
    val context = LocalContext.current
    val currentPath by rememberUpdatedState(newValue = languagePathImage)

    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    LaunchedEffect(key1 = currentPath) {
        bitmap = loadBitmapFromAssets(context, currentPath)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { onItemClick(idLanguageVariant) }, // Llamamos a la función lambda al hacer clic
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Orange), // Color(1.0f, 0.54117647f, 0.0f)
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = languageName,
                    modifier = Modifier
                        .size(85.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = languageName, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                Text(text = variantRegionName, fontSize = 14.sp)
                Text(text = variantName, fontSize = 14.sp)
            }
        }
    }
}

//@Preview(showSystemUi = true)
@Composable
//fun LanguageImage() {
fun LanguageImage(pathImage: String, contentDescription: String, variantName: String) {
    val context = LocalContext.current
    //val currentPath by rememberUpdatedState(newValue = "imagenes/lenguajes/mixteco.png")
    val currentPath by rememberUpdatedState(newValue = pathImage)


    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    LaunchedEffect(key1 = currentPath) {
        bitmap = loadBitmapFromAssets(context, currentPath)
    }
    bitmap?.let{
        Column(
            modifier = Modifier.width(140.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(60.dp) // Tamaño original
                    .clip(CircleShape)
            )
            Text(text = variantName, fontSize = 14.sp, textAlign = TextAlign.Center,)
        }

    }
}