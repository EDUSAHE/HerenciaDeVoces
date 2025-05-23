package com.example.herenciadevoces.domain.LanguageWordDate.model

import com.example.herenciadevoces.domain.LanguageVariant.model.LanguageANDLanguageVariant
import com.example.herenciadevoces.data.local.model.LanguageWordDataEntity

data class LanguageWordData(
    val idLanguageWordData: Int,
    val idSpanishWordData: Int,
    val idSemanticField: Int,
    val idLanguageVariant: Int,
    val pathAudio: String,
    val languageWord: String
)

fun LanguageWordDataEntity.toLanguageWordData(): LanguageWordData {
    return LanguageWordData(
        idLanguageWordData = idLanguageWordData,
        idSpanishWordData = idSpanishWordData,
        idSemanticField = idSemanticField,
        idLanguageVariant = idLanguageVariant,
        pathAudio = pathAudio,
        languageWord = languageWord
    )
}