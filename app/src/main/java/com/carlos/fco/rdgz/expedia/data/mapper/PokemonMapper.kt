package com.carlos.fco.rdgz.expedia.data.mapper

import com.carlos.fco.rdgz.expedia.data.dto.PokemonListItem
import com.carlos.fco.rdgz.expedia.domain.model.Pokemon

fun PokemonListItem.toPokemon() = Pokemon(
    name = name,
    url = url,
)