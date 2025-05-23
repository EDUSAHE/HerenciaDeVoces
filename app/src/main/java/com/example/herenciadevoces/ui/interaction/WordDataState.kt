package com.example.herenciadevoces.ui.interaction

import com.example.herenciadevoces.domain.SpanishWordData.model.SpanishWordData

data class WordDataState(
    val wordData: List<SpanishWordData> = emptyList()
)