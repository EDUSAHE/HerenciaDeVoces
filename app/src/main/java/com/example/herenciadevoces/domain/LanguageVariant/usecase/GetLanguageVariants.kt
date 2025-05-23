package com.example.herenciadevoces.domain.LanguageVariant.usecase

import com.example.herenciadevoces.domain.LanguageVariant.model.LanguageANDLanguageVariant
import com.example.herenciadevoces.data.local.repository.LanguageVariantRepository
import javax.inject.Inject

class GetLanguageVariants @Inject constructor(
    private val languageVariantRepository: LanguageVariantRepository
){
    suspend operator fun invoke(): List<LanguageANDLanguageVariant> {
        return languageVariantRepository.getAllLanguageANDLanguageVariants()
    }
}