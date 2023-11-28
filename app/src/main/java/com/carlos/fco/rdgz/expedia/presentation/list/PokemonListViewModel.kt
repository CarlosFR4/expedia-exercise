package com.carlos.fco.rdgz.expedia.presentation.list

import android.content.SharedPreferences
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val preferences: SharedPreferences,
    private val pokemonRepository: PokemonRepository,
): ViewModel() {

    fun getPokemonList() = pokemonRepository.getPokemonList(10)

    fun updatePokemonListIndex(index: Int) {
        preferences.edit().putInt("lastVisibleItemIndex", index).apply()
    }

    fun getLastVisiblePokemonIndex() = preferences.getInt("lastVisibleItemIndex", 0)
}