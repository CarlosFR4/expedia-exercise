package com.carlos.fco.rdgz.expedia.data.dto

data class PokemonListResponse(
val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListItem>
)
