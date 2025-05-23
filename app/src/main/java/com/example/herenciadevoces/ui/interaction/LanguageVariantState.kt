package com.example.herenciadevoces.ui.interaction

import com.example.herenciadevoces.domain.LanguageVariant.model.LanguageANDLanguageVariant

data class LanguageVariantState (
    var languageVariants: List<LanguageANDLanguageVariant> = emptyList()
)