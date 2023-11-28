package com.carlos.fco.rdgz.expedia.presentation.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.carlos.fco.rdgz.expedia.presentation.core.ErrorScreen
import com.carlos.fco.rdgz.expedia.presentation.core.LoadingScreen


@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    onSeeDetails: (Int) -> Unit,
) {
    val (index, offset) = viewModel.getIndexAndOffset()
    val listState: LazyListState = rememberLazyListState(index, offset)

    val pokemon = viewModel.pokemonList.collectAsLazyPagingItems()

    LaunchedEffect(key1 = pokemon.loadState.refresh) {
        when(pokemon.loadState.refresh) {
            is LoadState.NotLoading -> {
                if (listState.firstVisibleItemIndex != index) {
                    listState.scrollToItem(index, offset)
                }
            }
            else -> {  }
        }
    }

    LazyColumn(state = listState) {
        items(pokemon) { pokemonItem ->
            pokemonItem?.run {
                PokemonItem(pokemon = this) {
                    onSeeDetails(it.orderNumber ?: -1)
                    viewModel.setIndexAndOffset(
                        index = listState.firstVisibleItemIndex,
                        offset = listState.firstVisibleItemScrollOffset,
                    )
                }
            }
        }

        pokemon.loadState.apply {
            when (refresh) {
                is LoadState.Error -> {
                    item {
                        ErrorScreen(modifier = Modifier.fillParentMaxSize())
                    }
                }

                is LoadState.Loading -> {
                    item {
                        LoadingScreen(modifier = Modifier.fillParentMaxSize())
                    }
                }

                else -> {}
            }

            when (append) { // Pagination
                is LoadState.Error -> {
                    item { ErrorItem() }
                }

                is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                else -> {}
            }
        }
    }
}