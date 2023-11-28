package com.carlos.fco.rdgz.expedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlos.fco.rdgz.expedia.presentation.details.PokemonDetailsScreen
import com.carlos.fco.rdgz.expedia.presentation.list.PokemonListScreen
import com.carlos.fco.rdgz.expedia.presentation.list.PokemonListViewModel
import com.carlos.fco.rdgz.expedia.presentation.list.RollingPokeball
import com.carlos.fco.rdgz.expedia.presentation.navigation.Route
import com.carlos.fco.rdgz.expedia.ui.theme.CarlosRodriguezExpediaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarlosRodriguezExpediaTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
                        ) {
                            PokemonDetailsScreen(
                                viewModel = hiltViewModel(),
                                pokemonId = it.arguments?.getInt("pokemonId") ?: -1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            fontSize = 30.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarlosRodriguezExpediaTheme {
        Greeting("Android")
    }
}