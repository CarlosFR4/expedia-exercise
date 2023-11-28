package com.carlos.fco.rdgz.expedia.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlos.fco.rdgz.expedia.presentation.details.PokemonDetailsScreen
import com.carlos.fco.rdgz.expedia.presentation.list.PokemonListScreen
import com.carlos.fco.rdgz.expedia.presentation.list.PokemonListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.POKEMON_LIST
    ) {
        composable(Route.POKEMON_LIST) {
            PokemonListScreen(
                viewModel = hiltViewModel(),
                onSeeDetails = { pokemonId ->
                    navController.navigate(Route.POKEMON_DETAIL + "/$pokemonId")
                }
            )
        }
        composable(
            Route.POKEMON_DETAIL + "/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.IntType
                }
            )
        ) { route ->
            route.arguments?.getInt("pokemonId")?.let {
                PokemonDetailsScreen(
                    viewModel = hiltViewModel(),
                    pokemonId = it
                )
            } ?: run {

            }
        }
    }
}