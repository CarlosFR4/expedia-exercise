package com.carlos.fco.rdgz.expedia.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.carlos.fco.rdgz.expedia.presentation.core.RollingPokeball


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

        when (pokemon.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                //TODO Error Item
                //state.error to get error message
            }

            is LoadState.Loading -> { // Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        RollingPokeball(size = 50.dp)
                    }
                }
            }

            is LoadState.NotLoading -> {
                //TODO Not Loading UI
            }

            else -> {}
        }

        when (pokemon.loadState.append) { // Pagination
            is LoadState.Error -> {
                //TODO Pagination Error Item
                //state.error to get error message
            }

            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
    }
}