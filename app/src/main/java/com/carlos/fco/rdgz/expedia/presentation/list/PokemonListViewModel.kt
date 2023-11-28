package com.carlos.fco.rdgz.expedia.presentation.list

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.carlos.fco.rdgz.expedia.domain.usecase.GetPokemonListPaginationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val preferences: SharedPreferences,
    getPokemonListPaginationUseCase: GetPokemonListPaginationUseCase
): ViewModel() {

    val pokemonList = getPokemonListPaginationUseCase.execute().cachedIn(viewModelScope)

    fun getIndexAndOffset(): Pair<Int, Int> {
        return Pair(preferences.getInt("index", 0), preferences.getInt("offset", 0))
    }

    fun setIndexAndOffset(index: Int, offset: Int) {
        preferences.edit().putInt("index", index).putInt("offset", offset).apply()
    }
}