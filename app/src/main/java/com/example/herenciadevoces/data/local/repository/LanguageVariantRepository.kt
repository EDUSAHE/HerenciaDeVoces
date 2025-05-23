package com.example.herenciadevoces.data.local.repository

import com.example.herenciadevoces.data.local.dao.LanguageVariantDAO
import com.example.herenciadevoces.domain.LanguageVariant.model.LanguageANDLanguageVariant
import javax.inject.Inject

class LanguageVariantRepository @Inject constructor(
    private val languageVariantDAO: LanguageVariantDAO
){
    fun getAllLanguageANDLanguageVariants() : List<LanguageANDLanguageVariant>{
        return languageVariantDAO.getAllLanguageANDLanguageVariants()

    }

    fun getLanguageANDLanguageVariantByIds(idLanguageVariant: List<Int>) : List<LanguageANDLanguageVariant>{
        return languageVariantDAO.getLanguageANDLanguageVariantByIds(idLanguageVariant)
    }
}