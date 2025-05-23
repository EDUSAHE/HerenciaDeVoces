package com.example.herenciadevoces.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.herenciadevoces.domain.LanguageVariant.model.LanguageANDLanguageVariant
import com.example.herenciadevoces.data.local.model.LanguageVariantEntity

@Dao
interface LanguageVariantDAO {

    //@Query("SELECT *, language.languageName, language.pathImage FROM languageVariant JOIN language ON languageVariant.idLanguage = language.idLanguage")
    @Query("SELECT lv.*, l.languageName, l.pathImage FROM languageVariant AS lv INNER JOIN language AS l ON lv.idLanguage = l.idLanguage")
    fun getAllLanguageANDLanguageVariants(): List<LanguageANDLanguageVariant>

    @Query("SELECT lv.*, l.languageName, l.pathImage FROM languageVariant AS lv INNER JOIN language AS l ON lv.idLanguage = l.idLanguage WHERE lv.idLanguageVariant IN (:idLanguageVariants)")
    fun getLanguageANDLanguageVariantByIds(idLanguageVariants: List<Int>): List<LanguageANDLanguageVariant>

    @Query("SELECT * FROM languageVariant WHERE idLanguageVariant IN (:idsVariant)")
    fun getVariantsByIds(idsVariant: List<Int>): List<LanguageVariantEntity>

}