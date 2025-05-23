package com.example.herenciadevoces.ui.interaction

import com.example.herenciadevoces.domain.LanguageVariant.model.LanguageANDLanguageVariant

data class LanguageANDLanguageVariantState(
    val languageVariants: List<LanguageANDLanguageVariant> = emptyList()
)
