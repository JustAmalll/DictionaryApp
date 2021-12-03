package dev.amal.dictionaryapp.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import dev.amal.dictionaryapp.feature_dictionary.data.util.JsonParser
import dev.amal.dictionaryapp.feature_dictionary.domain.model.Meaning

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> =
        jsonParser.fromJson<ArrayList<Meaning>>(
            json, object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()

    @TypeConverter
    fun toMeaningsJson(meaning: List<Meaning>): String =
        jsonParser.toJson(
            meaning,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
}