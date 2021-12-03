package dev.amal.dictionaryapp.feature_dictionary.domain.repository

import dev.amal.dictionaryapp.core.util.Resource
import dev.amal.dictionaryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}