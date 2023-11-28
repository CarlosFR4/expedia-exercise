package com.carlos.fco.rdgz.expedia.domain.usecase

import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend fun execute(id: Int) = repository.getPokemon(id)
}