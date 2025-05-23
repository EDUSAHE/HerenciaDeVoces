package com.example.herenciadevoces.ui.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.herenciadevoces.ui.views.LanguageSelectionScreen
import com.example.herenciadevoces.ui.views.SemanticFieldSelectionScreen
import com.example.herenciadevoces.ui.views.WordSoundsScreen
import kotlinx.serialization.Serializable

@Serializable
object VariantSelection

@Serializable
data class SemanticFieldSelection(val idsVariants: List<Int>)

@Serializable
data class WordSounds(val idsVariants: List<Int>, val idSemanticField: Int, val semanticFieldName: String)

@Serializable
object LanguageSelection

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val listIdsLanguageVariants = remember { mutableStateListOf<Int>() }
    NavHost(
        navController = navController,
        //startDestination = VariantSelection
        startDestination = LanguageSelection
        //startDestination = VariantSelection
    ) {

        composable<LanguageSelection> {
            LanguageSelectionScreen() { idsLanguageVariants ->
                navController.navigate(SemanticFieldSelection(idsLanguageVariants))
                Log.d("LIST IDS LANGUAGE VARIANTS ", idsLanguageVariants.toList().toString())
                listIdsLanguageVariants.addAll(idsLanguageVariants)
            }

        }

        /*
                composable<LanguageSelection> {
            LanguageSelectionScreen(languageSelectionViewModel) { idsLanguages ->
                navController.navigate(SemanticFieldSelection)
                WordSounds(
                    idsLanguages = listIdsVariants.toList()
                )
            }
        }
        composable<VariantSelection> {
            VariantSelectionScreen(variantSelectionViewModel) { idsLanguagesVariants ->
                navController.navigate(SemanticFieldSelection)
                listIdsVariants.clear()
                listIdsVariants.addAll(idsLanguagesVariants)
                Log.d("LIST IDS VARIANTS ", listIdsVariants.toString())
            }
        }
        */



        composable<SemanticFieldSelection> {
            SemanticFieldSelectionScreen { idSemanticField, semanticFieldName ->
                navController.navigate(
                    WordSounds(
                        idsVariants = listIdsLanguageVariants.toList(),
                        idSemanticField = idSemanticField,
                        semanticFieldName = semanticFieldName
                    )
                )
            }
        }

        composable<WordSounds> {
            WordSoundsScreen()
        }
    }

}