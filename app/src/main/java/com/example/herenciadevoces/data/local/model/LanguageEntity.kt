package com.example.herenciadevoces.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="language")
data class LanguageEntity(
    @PrimaryKey(autoGenerate = true)val idLanguage: Int = 1,
    @ColumnInfo(name = "languageName")val languageName: String,
    @ColumnInfo(name = "pathImage")val pathImage: String
)