package com.example.herenciadevoces.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.herenciadevoces.domain.LanguageVariant.usecase.GetLanguageANDLanguageVariantByIds
import com.example.herenciadevoces.domain.LanguageWordDate.usecase.GetLWDBySWDandLV
import com.example.herenciadevoces.domain.SpanishWordData.model.SpanishWordData
import com.example.herenciadevoces.domain.SpanishWordData.usecase.GetSpanishWordDataBySF
import com.example.herenciadevoces.ui.interaction.LanguageANDLanguageVariantState
import com.example.herenciadevoces.ui.interaction.WordDataState
import com.example.herenciadevoces.ui.main.WordSounds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WordSoundsViewModel @Inject constructor(
    private val getSpanishWordDataBySF: GetSpanishWordDataBySF,
    private val getLWDBySWDandLV: GetLWDBySWDandLV,
    private val getLanguageANDLanguageVariantByIds: GetLanguageANDLanguageVariantByIds,
    savedStateHandle: SavedStateHandle,
) : ViewModel()  {
    private val wordSounds = savedStateHandle.toRoute<WordSounds>()
    val semanticFieldName: StateFlow<String> = MutableStateFlow(wordSounds.semanticFieldName).asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _wordDataState = MutableStateFlow(WordDataState())
    val wordDataState: StateFlow<WordDataState> = _wordDataState

    private val _index = MutableStateFlow(0)
    val index: StateFlow<Int> = _index.asStateFlow()

    val actualItem: StateFlow<SpanishWordData?> = combine(wordDataState, index) { wordDataState, index ->
        wordDataState.wordData.getOrNull(index)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        collectItems()
    }


    private fun collectItems(){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val fetchedSpanishWordData = getSpanishWordDataBySF(wordSounds.idSemanticField)
                fetchedSpanishWordData.forEach { spanishWordData ->
                    val fetchedLanguageWordData = getLWDBySWDandLV(spanishWordData.idSpanishWordData,wordSounds.idsVariants)
                    spanishWordData.lWD.addAll(fetchedLanguageWordData)
                }



                //Log.d("LanguagVariantsData", fetchedLanguageVariants.toString())
                Log.d("ITEMS DATA", fetchedSpanishWordData.toString())

                withContext(Dispatchers.Main) {
                    _wordDataState.value = _wordDataState.value.copy(wordData = fetchedSpanishWordData)
                    //_languageVariantsState.value = _languageVariantsState.value.copy(languageVariants = fetchedLanguageVariants)
                    _isLoading.value = false // Set loading to false after success
                }
            } catch (e: Exception) {
                Log.e("WordSoundsViewModel", "Error fetching data:", e)
                _isLoading.value = false
            }
        }
    }

    fun nextItem() {
        if (index.value < wordDataState.value.wordData.size -1) {
            _index.value++
            Log.d("INDEX", index.value.toString())

        }
    }

    fun previousItem() {
        if (index.value > 0) {
            _index.value--
        }
    }
}