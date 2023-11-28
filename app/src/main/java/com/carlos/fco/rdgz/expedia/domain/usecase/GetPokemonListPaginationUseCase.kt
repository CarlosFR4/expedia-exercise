package com.carlos.fco.rdgz.expedia.domain.usecase

import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import javax.inject.Inject

class GetPokemonListPaginationUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    fun execute() = repository.getPokemonList()
}