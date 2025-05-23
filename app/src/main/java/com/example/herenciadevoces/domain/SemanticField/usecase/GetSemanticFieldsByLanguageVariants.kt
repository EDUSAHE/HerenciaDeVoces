package com.example.herenciadevoces.domain.SemanticField.usecase

import com.example.herenciadevoces.data.local.repository.SemanticFieldRepository
import com.example.herenciadevoces.domain.SemanticField.model.SemanticField
import com.example.herenciadevoces.domain.SemanticField.model.toSemanticField
import javax.inject.Inject


class GetSemanticFieldsByLanguageVariants @Inject constructor(
    private val semanticFieldRepository: SemanticFieldRepository
) {
    suspend operator fun invoke(idsLanguageVariants: List<Int>):List<SemanticField>{
        return semanticFieldRepository.getSemanticFieldsByLanguageVariants(idsLanguageVariants).map { it.toSemanticField() }
    }
}