package com.example.herenciadevoces.ui.utils

import com.example.herenciadevoces.domain.LanguageWordDate.model.LanguageWordData
import com.example.herenciadevoces.domain.LanguageWordDate.model.LanguageWordDataAndLV

data class LanguageVariantData(
    val lWD : List<LanguageWordDataAndLV> = emptyList()
)