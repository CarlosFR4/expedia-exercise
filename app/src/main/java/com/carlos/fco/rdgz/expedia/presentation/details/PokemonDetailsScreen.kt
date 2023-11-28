package com.carlos.fco.rdgz.expedia.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.carlos.fco.rdgz.expedia.ProjectConfig
import com.carlos.fco.rdgz.expedia.R
import com.carlos.fco.rdgz.expedia.capitalize
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon
import com.carlos.fco.rdgz.expedia.presentation.core.ErrorScreen
import com.carlos.fco.rdgz.expedia.presentation.list.ErrorItem
import com.carlos.fco.rdgz.expedia.toPokemonOrderNumber

@Composable
fun PokemonDetailsScreen(
    pokemonId: Int,
    viewModel: PokemonDetailsViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val pokemon: Pokemon by viewModel.getPokemonDetails(pokemonId).observeAsState(
            Pokemon(
                name = "Default",
                height = 0,
                orderNumber = 0,
                weight = 0,
                url = ""
            )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(2.dp))
                .padding(16.dp)
        ) {
            AsyncImage(
                model = pokemon.orderNumber?.let { ProjectConfig.POKEMON_IMAGE_URL + pokemonId + ".png" }
                    ?: R.drawable.missing_image,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(2.dp)) // Optional: makes the image circular
                    .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(2.dp))
                .padding(16.dp)
        ) {
            Text(
                text = pokemon.orderNumber?.toPokemonOrderNumber() ?: "#0000",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.size(1.dp))

            Text(text = pokemon.name.capitalize(), style = MaterialTheme.typography.displayLarge)

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = stringResource(id = R.string.pokemon_height, pokemon.height ?: 0),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.pokemon_weight, pokemon.weight ?: 0),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}
