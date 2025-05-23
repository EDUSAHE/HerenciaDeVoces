package com.example.herenciadevoces.domain.LanguageWordDate.usecase

import com.example.herenciadevoces.data.local.repository.LanguageWordDataRepository
import com.example.herenciadevoces.domain.LanguageWordDate.model.LanguageWordData
import com.example.herenciadevoces.domain.LanguageWordDate.model.LanguageWordDataAndLV
import com.example.herenciadevoces.domain.LanguageWordDate.model.toLanguageWordData
import javax.inject.Inject

class GetLWDBySWDandLV @Inject constructor(
    private val languageWordDataRepository: LanguageWordDataRepository
) {
    suspend operator fun invoke(idSpanishWordData: Int, idsLanguagesVariants: List<Int>): List<LanguageWordDataAndLV> {
        return languageWordDataRepository.getLWDByIdSWDandLV(idSpanishWordData,idsLanguagesVariants)
    }
}