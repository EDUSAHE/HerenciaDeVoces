package com.example.herenciadevoces.domain.LanguageWordDate.model

import com.example.herenciadevoces.domain.LanguageVariant.model.LanguageANDLanguageVariant

data class LanguageWordDataAndLV(
    val idLanguageWordData: Int,
    val idSpanishWordData: Int,
    val idSemanticField: Int,
    val idLanguageVariant: Int,
    val pathAudio: String,
    val languageWord: String,
    val languageName: String,
    val pathImage: String,
    val variantName: String,
)
