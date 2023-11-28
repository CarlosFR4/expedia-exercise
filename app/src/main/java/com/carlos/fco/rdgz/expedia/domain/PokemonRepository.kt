package com.carlos.fco.rdgz.expedia.domain

import androidx.paging.PagingData
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(itemsPerPage: Int): Flow<PagingData<Pokemon>>
}