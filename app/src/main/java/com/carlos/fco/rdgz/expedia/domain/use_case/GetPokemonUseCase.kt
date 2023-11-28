package com.carlos.fco.rdgz.expedia.domain.use_case

import com.carlos.fco.rdgz.expedia.domain.PokemonRepository

class GetPokemonUseCase(
    private val repository: PokemonRepository,
) {
    suspend fun execute(offset: Int) = repository.getPokemonList(offset)
}