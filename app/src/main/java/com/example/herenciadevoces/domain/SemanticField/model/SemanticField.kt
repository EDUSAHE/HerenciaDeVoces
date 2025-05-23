package com.example.herenciadevoces.domain.SemanticField.model

import com.example.herenciadevoces.data.local.model.SemanticFieldEntity

data class SemanticField(
    val idSemanticField: Int,
    val semanticFieldName: String,
    val pathImage: String
)

fun SemanticFieldEntity.toSemanticField(): SemanticField {
    return SemanticField(
        idSemanticField = idSemanticField,
        semanticFieldName = semanticFieldName,
        pathImage = pathImage
    )
}

fun SemanticField.toSemanticFieldEntity(): SemanticFieldEntity {
    return SemanticFieldEntity(
        idSemanticField = idSemanticField,
        semanticFieldName = semanticFieldName,
        pathImage = pathImage
    )
}