package com.hfad.online_bookstore.di

import com.hfad.online_bookstore.data.repository.LibraryRepositoryImpl
import com.hfad.online_bookstore.data.sources.api.BookLibraryApi
import com.hfad.online_bookstore.data.usecases.FetchDataByBookIsbnUseCase
import com.hfad.online_bookstore.data.usecases.FetchDataByBookIsbnUseCaseImpl
import com.hfad.online_bookstore.data.usecases.FetchDataByTitleUseCase
import com.hfad.online_bookstore.data.usecases.FetchDataByTitleUseCaseImpl
import com.hfad.online_bookstore.domain.repository.LibraryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): BookLibraryApi{
        return Retrofit.Builder()
            .baseUrl("https://openlibrary.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookLibraryApi::class.java)
    }



    @Provides
    @Singleton
    fun provideLibraryRepository(api: BookLibraryApi): LibraryRepository {
        return LibraryRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFetchDataResultByTitleUseCase(repository: LibraryRepository): FetchDataByTitleUseCase {
        return FetchDataByTitleUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideFetchDataResultByIsbnUseCase(repository: LibraryRepository): FetchDataByBookIsbnUseCase {
        return FetchDataByBookIsbnUseCaseImpl(repository)
    }
}