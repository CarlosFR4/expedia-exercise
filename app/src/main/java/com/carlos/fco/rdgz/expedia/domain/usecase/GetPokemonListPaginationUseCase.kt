package com.carlos.fco.rdgz.expedia.domain.usecase

import androidx.paging.cachedIn
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class GetPokemonListPaginationUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    fun execute() = repository.getPokemonList()
}