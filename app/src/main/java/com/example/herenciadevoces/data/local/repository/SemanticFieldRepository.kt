package com.example.herenciadevoces.data.local.repository

import android.util.Log
import com.example.herenciadevoces.data.local.dao.SemanticFieldDAO
import com.example.herenciadevoces.data.local.model.SemanticFieldEntity
import javax.inject.Inject

class SemanticFieldRepository @Inject constructor(
    private val semanticFieldDAO: SemanticFieldDAO
) {
    fun getAllSemanticFields():List<SemanticFieldEntity>{
        return semanticFieldDAO.getAllSemanticFields()
    }

    fun getSemanticFieldsByLanguageVariants(idsLanguageVariants:List<Int>):List<SemanticFieldEntity>{
        return semanticFieldDAO.getSematicFieldsByLanguageVariants(idsLanguageVariants)
    }
}