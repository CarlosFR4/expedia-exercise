package com.carlos.fco.rdgz.expedia.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.carlos.fco.rdgz.expedia.data.mapper.toPokemon
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon

class PokemonPagingSource(
    private val service: PokemonService,
) : PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let {page ->
                page.nextKey?.plus(1) ?: page.prevKey?.minus(1)
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> = try {
        val page = params.key ?: 1
        val offset = (page - 1) * params.loadSize
        service.getPokemonList(
            offset = offset,
            limit = params.loadSize,
        ).let { response ->
            LoadResult.Page(
                data = response.results.map { it.toPokemon() },
                prevKey = null,
                nextKey = response.next?.run { page.plus(1) },
                itemsBefore = offset
            )
        }
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}