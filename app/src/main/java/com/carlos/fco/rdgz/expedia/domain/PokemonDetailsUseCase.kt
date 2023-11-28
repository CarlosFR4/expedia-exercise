package com.carlos.fco.rdgz.expedia.domain

import javax.inject.Inject

class PokemonDetailsUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(id: Int) = pokemonRepository.getPokemon(id)
}