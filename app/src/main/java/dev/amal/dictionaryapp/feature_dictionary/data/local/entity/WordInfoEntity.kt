package dev.amal.dictionaryapp.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.amal.dictionaryapp.feature_dictionary.domain.model.Meaning
import dev.amal.dictionaryapp.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    @PrimaryKey val id: Int? = null,
    val word: String,
    val phonetic: String?,
    val origin: String?,
    val meanings: List<Meaning>
) {
    fun toWordInfo(): WordInfo = WordInfo(
        meanings = meanings,
        word = word,
        origin = origin,
        phonetic = phonetic
    )
}
