package com.carlos.fco.rdgz.expedia.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.carlos.fco.rdgz.expedia.R
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: (Pokemon) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(pokemon) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
        ) {
            AsyncImage(
                model = pokemon.orderNumber?.let { "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${it}.png" }
                    ?: R.drawable.missing_image,
                contentDescription = null,
                modifier = Modifier
                    .size(112.dp),
                placeholder = rememberAsyncImagePainter(model = R.drawable.pokeball),
            )
            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = pokemon.orderNumber.toString(),
                fontSize = 10.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = pokemon.name,
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}