package com.carlos.fco.rdgz.expedia.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.carlos.fco.rdgz.expedia.ProjectConfig
import com.carlos.fco.rdgz.expedia.data.mapper.toPokemon
import com.carlos.fco.rdgz.expedia.domain.PokemonRepository
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val api: PokemonService,
) : PokemonRepository {
    private val pager = Pager(
        config = PagingConfig(
            pageSize = ProjectConfig.PAGINATION_SIZE,
            initialLoadSize = ProjectConfig.INITIAL_LOAD_SIZE,
        ),
        pagingSourceFactory = {
            PokemonPagingSource(
                service = api,
            )
        }
    )
    override fun getPokemonList(): Flow<PagingData<Pokemon>> = pager.flow

    override suspend fun getPokemon(id: Int): Pokemon = api.getPokemon(id).toPokemon()
}