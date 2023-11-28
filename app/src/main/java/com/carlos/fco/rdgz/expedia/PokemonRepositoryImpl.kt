package com.carlos.fco.rdgz.expedia

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.carlos.fco.rdgz.expedia.data.PokemonPagingSource
import com.carlos.fco.rdgz.expedia.data.PokemonService
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val api: PokemonService,
) : PokemonRepository {
    override fun getPokemonList(itemsPerPage: Int): Flow<PagingData<Pokemon>> = Pager(
        config = PagingConfig(
            pageSize = itemsPerPage,
            initialLoadSize = itemsPerPage,
        ),
        pagingSourceFactory = {
            PokemonPagingSource(
                service = api,
            )
        }
    ).flow
}