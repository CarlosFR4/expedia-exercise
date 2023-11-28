package com.carlos.fco.rdgz.expedia.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.carlos.fco.rdgz.expedia.R
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon

@Composable
fun PokemonDetailsScreen(pokemonId: Int, viewModel: PokemonDetailsViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val pokemon: Pokemon by viewModel.getDetails(pokemonId).observeAsState(
            Pokemon(
                name = "Default",
                height = 0,
                orderNumber = 0,
                weight = 0,
                url = ""
            )
        )

        AsyncImage(
            model = pokemon.orderNumber?.let { "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${it}.png" }
                ?: R.drawable.missing_image,
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(2.dp)) // Optional: makes the image circular
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = pokemon.name, style = MaterialTheme.typography.titleMedium)
        Text(text = "Height: ${pokemon.height} cm", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Order Number: ${pokemon.orderNumber}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(text = "Weight: ${pokemon.weight} kg", style = MaterialTheme.typography.bodyMedium)
    }
}
