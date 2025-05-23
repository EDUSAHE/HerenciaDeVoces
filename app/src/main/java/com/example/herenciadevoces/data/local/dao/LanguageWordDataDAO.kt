package com.example.herenciadevoces.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.herenciadevoces.data.local.model.LanguageWordDataEntity
import com.example.herenciadevoces.domain.LanguageWordDate.model.LanguageWordDataAndLV

@Dao
interface LanguageWordDataDAO {
    @Query("SELECT lwd.*,l.languageName,l.pathImage,lv.variantName FROM LanguageWordData lwd JOIN LanguageVariant lv ON lwd.idLanguageVariant = lv.idLanguageVariant JOIN Language l ON lv.idLanguage = l.idLanguage WHERE lwd.idSpanishWordData = :idSpanishWordData AND lwd.idLanguageVariant IN (:idsLanguagesVariants)")
    fun getLWDByIdSWDandLV(idSpanishWordData: Int,idsLanguagesVariants: List<Int>): List<LanguageWordDataAndLV>

}