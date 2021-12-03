package dev.amal.dictionaryapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.dictionaryapp.feature_dictionary.data.local.Converters
import dev.amal.dictionaryapp.feature_dictionary.data.local.WordInfoDatabase
import dev.amal.dictionaryapp.feature_dictionary.data.local.WordInfoDatabase.Companion.DATABASE_NAME
import dev.amal.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import dev.amal.dictionaryapp.feature_dictionary.data.repository.WordInfoRepositoryImpl
import dev.amal.dictionaryapp.feature_dictionary.data.util.GsonParser
import dev.amal.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import dev.amal.dictionaryapp.feature_dictionary.domain.use_case.GetWordInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo =
        GetWordInfo(repository)

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository = WordInfoRepositoryImpl(api, db.dao)

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase =
        Room.databaseBuilder(
            app, WordInfoDatabase::class.java, DATABASE_NAME
        ).addTypeConverter(Converters(GsonParser(Gson()))).build()

    @Provides
    @Singleton
    fun providesDictionaryApi(): DictionaryApi =
        Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
}