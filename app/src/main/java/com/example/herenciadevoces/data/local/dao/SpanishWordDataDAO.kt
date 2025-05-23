package com.example.herenciadevoces.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.herenciadevoces.data.local.model.SpanishWordDataEntity

@Dao
interface SpanishWordDataDAO {
    @Query("SELECT * FROM SpanishWordData WHERE idSemanticField = :idSemanticField")
    fun getSpanishWordDataBySemanticField(idSemanticField: Int): List<SpanishWordDataEntity>
}