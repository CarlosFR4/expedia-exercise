package com.carlos.fco.rdgz.expedia.data

import com.carlos.fco.rdgz.expedia.data.dto.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonListResponse

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}