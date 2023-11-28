package com.carlos.fco.rdgz.expedia

import com.carlos.fco.rdgz.expedia.data.PokemonService
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon

class PokemonRepositoryImpl(
    private val api: PokemonService,
) : PokemonRepository {
    override suspend fun getPokemonList(offset: Int): List<Pokemon> =
        api.getPokemonList(offset).results.map {
            Pokemon(
                name = it.name,
                url = it.url,
            )
        }
}