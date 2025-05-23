package com.example.herenciadevoces.domain.SpanishWordData.usecase

import com.example.herenciadevoces.data.local.repository.SpanishWordDataRepository
import com.example.herenciadevoces.domain.SpanishWordData.model.SpanishWordData
import com.example.herenciadevoces.domain.SpanishWordData.model.toSpanishWordData
import javax.inject.Inject

class GetSpanishWordDataBySF @Inject constructor(
    private val spanishWordDataRepository: SpanishWordDataRepository
) {
    suspend operator fun invoke(idSemanticField: Int): List<SpanishWordData> {
        return spanishWordDataRepository.getSpanishWordDataBySemanticField(idSemanticField).map { it.toSpanishWordData() }
    }
}