package com.example.herenciadevoces.domain.Language.model

import com.example.herenciadevoces.data.local.model.LanguageEntity

data class Language(
    val idLanguage: Int,
    val languageName: String,
    val pathImage: String
)

fun LanguageEntity.toLanguage(): Language {
    return Language(
        idLanguage = idLanguage,
        languageName = languageName,
        pathImage = pathImage
    )
}

fun Language.toLanguageEntity(): LanguageEntity {
    return LanguageEntity(
        idLanguage = idLanguage,
        languageName = languageName,
        pathImage = pathImage
    )
}