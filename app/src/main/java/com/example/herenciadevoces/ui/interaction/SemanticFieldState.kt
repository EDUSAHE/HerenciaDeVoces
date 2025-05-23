package com.example.herenciadevoces.ui.interaction

import com.example.herenciadevoces.domain.SemanticField.model.SemanticField

data class SemanticFieldState (
    var semanticField: List<SemanticField> = emptyList()
)