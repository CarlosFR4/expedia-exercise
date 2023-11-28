package com.carlos.fco.rdgz.expedia.presentation.list

import androidx.lifecycle.ViewModel
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
): ViewModel() {

    fun getPokemonList() = pokemonRepository.getPokemonList(10)
}