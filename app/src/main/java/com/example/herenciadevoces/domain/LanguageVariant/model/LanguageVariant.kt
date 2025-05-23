package com.example.herenciadevoces.domain.LanguageVariant.model

import com.example.herenciadevoces.data.local.model.LanguageVariantEntity
import com.example.herenciadevoces.domain.Language.model.Language

data class LanguageVariant(
    val idLanguageVariant: Int,
    val idLanguage: Int,
    val variantName: String,
    val variantRegionName: String
)

fun LanguageVariantEntity.toLanguageVariant(): LanguageVariant {
    return LanguageVariant(
        idLanguageVariant = idLanguageVariant,
        idLanguage = idLanguage,
        variantName = variantName,
        variantRegionName = variantRegionName
    )
}

fun LanguageVariant.toLanguageVariantEntity(): LanguageVariantEntity {
    return LanguageVariantEntity(
        idLanguageVariant = idLanguageVariant,
        idLanguage = idLanguage,
        variantName = variantName,
        variantRegionName = variantRegionName
    )
}