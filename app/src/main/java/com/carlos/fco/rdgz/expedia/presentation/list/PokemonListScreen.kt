package com.carlos.fco.rdgz.expedia.presentation.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
) {
    Text(text = "Pokemon List Screen")
    runBlocking { viewModel.getPokemonList() }
}