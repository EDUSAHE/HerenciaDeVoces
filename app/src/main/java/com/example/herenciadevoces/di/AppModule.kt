package com.example.herenciadevoces.di

import android.content.Context
import androidx.room.Room
import com.example.herenciadevoces.data.local.dao.LanguageDAO
import com.example.herenciadevoces.data.local.dao.LanguageVariantDAO
import com.example.herenciadevoces.data.local.dao.LanguageWordDataDAO
import com.example.herenciadevoces.data.local.dao.SemanticFieldDAO
import com.example.herenciadevoces.data.local.dao.SpanishWordDataDAO
import com.example.herenciadevoces.data.local.database.HerenciadevocesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesherenciaDeVocesRoomDataBase(@ApplicationContext app: Context): HerenciadevocesDataBase {
        val database = Room.databaseBuilder(
            app,
            HerenciadevocesDataBase::class.java,
            name = "herenciaDeVocesDB.db"
        ).createFromAsset("database/herenciaDeVocesDB.db").build()

        return database
    }

    @Provides
    @Singleton
    fun providesSemanticFieldDao(herenciadevocesDataBase: HerenciadevocesDataBase): SemanticFieldDAO =
        herenciadevocesDataBase.semanticFieldDAO()

    @Provides
    @Singleton
    fun providesLanguageDao(herenciadevocesDataBase: HerenciadevocesDataBase): LanguageDAO =
        herenciadevocesDataBase.languageDAO()

    @Provides
    @Singleton
    fun providesLanguageVariantDao(herenciadevocesDataBase: HerenciadevocesDataBase): LanguageVariantDAO =
        herenciadevocesDataBase.languageVariant()

    @Provides
    @Singleton
    fun providesLanguageWordDataDao(herenciadevocesDataBase: HerenciadevocesDataBase): LanguageWordDataDAO =
        herenciadevocesDataBase.languageWordDataDAO()

    @Provides
    @Singleton
    fun providesSpanishWordDataDao(herenciadevocesDataBase: HerenciadevocesDataBase): SpanishWordDataDAO =
        herenciadevocesDataBase.spanishWordDataDAO()
}