package com.carlos.fco.rdgz.expedia.data.mapper

import com.carlos.fco.rdgz.expedia.data.dto.PokemonListItem
import com.carlos.fco.rdgz.expedia.data.dto.PokemonResponse
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon

fun PokemonListItem.toPokemon() = Pokemon(
    name = name,
    url = url,
    orderNumber = Regex("""https://pokeapi.co/api/v2/pokemon/(\d+)/""")
        .matchEntire(url)?.groupValues?.get(1)?.toInt(),
)

fun PokemonResponse.toPokemon() = Pokemon(
    name = name,
    height = height,
    weight = weight,
    orderNumber = id,
    url = "https://pokeapi.co/api/v2/pokemon/$id/",
)