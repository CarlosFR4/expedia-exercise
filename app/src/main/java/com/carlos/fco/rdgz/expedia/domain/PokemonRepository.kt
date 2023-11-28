package com.carlos.fco.rdgz.expedia.domain

import com.carlos.fco.rdgz.expedia.domain.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int): List<Pokemon>
}