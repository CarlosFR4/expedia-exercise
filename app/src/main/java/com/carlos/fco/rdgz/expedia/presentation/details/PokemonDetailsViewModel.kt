package com.carlos.fco.rdgz.expedia.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.carlos.fco.rdgz.expedia.domain.PokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonDetailsUseCase: PokemonDetailsUseCase,
): ViewModel() {
    fun getDetails(id: Int) = liveData { emit(pokemonDetailsUseCase.execute(id)) }
}