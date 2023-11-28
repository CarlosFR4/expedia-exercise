package com.carlos.fco.rdgz.expedia.data

import com.carlos.fco.rdgz.expedia.data.dto.PokemonListResponse
import com.carlos.fco.rdgz.expedia.data.dto.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int,
    ): PokemonResponse
}