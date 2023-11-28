package com.carlos.fco.rdgz.expedia.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.fco.rdgz.expedia.data.PokemonRepositoryImpl
import com.carlos.fco.rdgz.expedia.ProjectConfig
import com.carlos.fco.rdgz.expedia.data.PokemonService
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import com.carlos.fco.rdgz.expedia.domain.usecase.GetPokemonListPaginationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Scope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonService = Retrofit.Builder().baseUrl(ProjectConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(PokemonService::class.java)

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonService): PokemonRepository =
        PokemonRepositoryImpl(pokemonApi)

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(ProjectConfig.PREFERENCES_NAME, MODE_PRIVATE)
}