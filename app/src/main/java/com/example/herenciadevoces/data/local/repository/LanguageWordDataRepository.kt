package com.example.herenciadevoces.data.local.repository

import com.example.herenciadevoces.data.local.dao.LanguageWordDataDAO
import com.example.herenciadevoces.data.local.model.LanguageWordDataEntity
import com.example.herenciadevoces.domain.LanguageWordDate.model.LanguageWordDataAndLV
import javax.inject.Inject

class LanguageWordDataRepository @Inject constructor(
    private val languageWordDataDAO: LanguageWordDataDAO
) {
    fun getLWDByIdSWDandLV(idSpanishWordData: Int, idsLanguagesVariants: List<Int>): List<LanguageWordDataAndLV> {
        return languageWordDataDAO.getLWDByIdSWDandLV(idSpanishWordData,idsLanguagesVariants)
    }
}