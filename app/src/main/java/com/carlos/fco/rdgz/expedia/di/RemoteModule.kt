package com.carlos.fco.rdgz.expedia.di

import com.carlos.fco.rdgz.expedia.PokemonRepositoryImpl
import com.carlos.fco.rdgz.expedia.data.PokemonService
import com.carlos.fco.rdgz.expedia.data.TestData
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import com.carlos.fco.rdgz.expedia.domain.use_case.GetPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
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
}

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    @ViewModelScoped
    fun getPokemonUseCase(pokemonRepository: PokemonRepository): GetPokemonUseCase =
        GetPokemonUseCase(pokemonRepository)
}