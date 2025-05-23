package com.example.herenciadevoces.domain.LanguageVariant.model

data class LanguageANDLanguageVariant(
    val idLanguageVariant: Int,
    val idLanguage: Int,
    val variantName: String,
    val variantRegionName: String,
    val languageName: String,
    val pathImage: String,
)
