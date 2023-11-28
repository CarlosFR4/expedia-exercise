package com.carlos.fco.rdgz.expedia.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.carlos.fco.rdgz.expedia.domain.usecase.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
): ViewModel() {
    fun getPokemonDetails(id: Int) = liveData { emit(getPokemonDetailsUseCase.execute(id)) }
}