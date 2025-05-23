package com.example.herenciadevoces.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.herenciadevoces.data.local.model.SemanticFieldEntity

@Dao
    interface SemanticFieldDAO {
        @Query("SELECT * FROM semanticField")
        fun getAllSemanticFields(): List<SemanticFieldEntity>

        @Query("SELECT DISTINCT sf.* FROM semanticField sf JOIN languageWordData lwd ON lwd.idSemanticField = sf.idSemanticField WHERE lwd.idLanguageVariant IN (:idsLanguageVariants);")
        fun getSematicFieldsByLanguageVariants(idsLanguageVariants :List<Int>): List<SemanticFieldEntity>
    }