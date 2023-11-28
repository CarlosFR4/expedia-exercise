package com.carlos.fco.rdgz.expedia.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.carlos.fco.rdgz.expedia.PokemonRepositoryImpl
import com.carlos.fco.rdgz.expedia.data.PokemonService
import com.carlos.fco.rdgz.expedia.data.TestData
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideTestData(): TestData = TestData("This is a test")

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonService = Retrofit.Builder().baseUrl(PokemonService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(PokemonService::class.java)

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonService): PokemonRepository =
        PokemonRepositoryImpl(pokemonApi)

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences("PokemonPreferences", MODE_PRIVATE)
}