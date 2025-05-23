package com.example.herenciadevoces.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.herenciadevoces.domain.LanguageVariant.usecase.GetLanguageVariants
import com.example.herenciadevoces.ui.interaction.LanguageVariantState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val getLanguagesVariants: GetLanguageVariants
) : ViewModel() {
    private val _state = mutableStateOf(LanguageVariantState())
    private val _selectedLanguageVariants = MutableStateFlow<List<Int>>(emptyList())
    val state: State<LanguageVariantState> = _state
    val selectedLanguageVariants = _selectedLanguageVariants.asStateFlow()



    init {
        collectLanguageVariants()
    }

    private fun collectLanguageVariants(){
        viewModelScope.launch (Dispatchers.IO) {
            val fetchedLanguageVariants = getLanguagesVariants()
            Log.d("LANGUAGE VARIANTS", fetchedLanguageVariants.toString())
            withContext(Dispatchers.Main){
                _state.value = _state.value.copy(languageVariants = fetchedLanguageVariants)
            }
        }
    }
}