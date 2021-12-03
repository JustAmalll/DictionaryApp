package dev.amal.dictionaryapp.feature_dictionary.data.remote

import dev.amal.dictionaryapp.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{$SEARCH_QUERY}")
    suspend fun getWordInfo(
        @Path(SEARCH_QUERY) word: String
    ): List<WordInfoDto>

    companion object {
        const val SEARCH_QUERY = "word"
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }

}