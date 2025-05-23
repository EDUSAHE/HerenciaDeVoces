package com.example.herenciadevoces.domain.SemanticField.usecase

import com.example.herenciadevoces.data.local.repository.SemanticFieldRepository
import com.example.herenciadevoces.domain.SemanticField.model.SemanticField
import com.example.herenciadevoces.domain.SemanticField.model.toSemanticField
import javax.inject.Inject

class GetSemanticFields @Inject constructor(
    private val semanticFieldRepository: SemanticFieldRepository
){
    suspend operator fun invoke():List<SemanticField>{
        return semanticFieldRepository.getAllSemanticFields().map { it.toSemanticField() }
    }
}