package com.example.herenciadevoces.data.local.repository

import com.example.herenciadevoces.data.local.dao.SpanishWordDataDAO
import com.example.herenciadevoces.data.local.model.SpanishWordDataEntity
import javax.inject.Inject

class SpanishWordDataRepository @Inject constructor (
    private val spanishWordDataDAO: SpanishWordDataDAO
) {
    fun getSpanishWordDataBySemanticField(idSemanticField: Int): List<SpanishWordDataEntity> {
        return spanishWordDataDAO.getSpanishWordDataBySemanticField(idSemanticField)
    }
}