package com.example.herenciadevoces.domain.Language.usecase

import com.example.herenciadevoces.data.local.repository.LanguageRepository
import javax.inject.Inject

class GetLanguages @Inject constructor(
    private val languageRepository: LanguageRepository
){
    //suspend operator fun invoke():List<Language>{
    //  return languageRepository.getAllLanguage().map { it.toLanguage() }
    //}
}