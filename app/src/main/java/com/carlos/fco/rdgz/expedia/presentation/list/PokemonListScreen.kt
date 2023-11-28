package com.carlos.fco.rdgz.expedia.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.runBlocking

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
) {
    viewModel.getPokemonList().collectAsLazyPagingItems().let { pokemon ->
        LazyColumn {
            items(pokemon) { item ->
                item?.let { PokemonItem(pokemon = it) }
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
                else -> {}
            }

            when (val state = pokemon.loadState.append) { // Pagination
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
}