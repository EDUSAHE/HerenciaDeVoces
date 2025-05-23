package com.example.herenciadevoces.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.herenciadevoces.data.local.dao.LanguageDAO
import com.example.herenciadevoces.data.local.dao.LanguageVariantDAO
import com.example.herenciadevoces.data.local.dao.LanguageWordDataDAO
import com.example.herenciadevoces.data.local.dao.SemanticFieldDAO
import com.example.herenciadevoces.data.local.dao.SpanishWordDataDAO
import com.example.herenciadevoces.data.local.model.LanguageEntity
import com.example.herenciadevoces.data.local.model.LanguageVariantEntity
import com.example.herenciadevoces.data.local.model.LanguageWordDataEntity
import com.example.herenciadevoces.data.local.model.SemanticFieldEntity
import com.example.herenciadevoces.data.local.model.SpanishWordDataEntity


@Database(
    entities = [
        LanguageEntity::class,
        LanguageVariantEntity::class,
        LanguageWordDataEntity::class,
        SemanticFieldEntity::class,
        SpanishWordDataEntity::class
    ],
    version = 1)

abstract class HerenciadevocesDataBase : RoomDatabase() {
    abstract fun languageDAO(): LanguageDAO
    abstract fun languageVariant(): LanguageVariantDAO
    abstract fun languageWordDataDAO(): LanguageWordDataDAO
    abstract fun semanticFieldDAO(): SemanticFieldDAO
    abstract fun spanishWordDataDAO(): SpanishWordDataDAO
}
