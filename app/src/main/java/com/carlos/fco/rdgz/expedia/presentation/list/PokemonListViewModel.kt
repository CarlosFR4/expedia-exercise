package com.carlos.fco.rdgz.expedia.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.fco.rdgz.expedia.data.TestData
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import com.carlos.fco.rdgz.expedia.domain.use_case.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
): ViewModel() {

    fun getPokemonList() {
        viewModelScope.launch {
            val pokemonList = getPokemonUseCase.execute(0)
            Log.e("PokemonListViewModel", "PokemonList: $pokemonList")
        }
    }
}