package com.example.herenciadevoces.domain.LanguageVariant.usecase

import com.example.herenciadevoces.domain.LanguageVariant.model.LanguageANDLanguageVariant
import com.example.herenciadevoces.data.local.repository.LanguageVariantRepository
import javax.inject.Inject

class GetLanguageANDLanguageVariantByIds @Inject constructor(
    private val languageVariantRepository: LanguageVariantRepository
){
    suspend operator fun invoke(idLanguageVariant: List<Int>): List<LanguageANDLanguageVariant> {
        return languageVariantRepository.getLanguageANDLanguageVariantByIds(idLanguageVariant)
    }
}