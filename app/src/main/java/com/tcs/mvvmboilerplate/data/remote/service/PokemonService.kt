package com.tcs.mvvmboilerplate.data.remote.service

import com.tcs.mvvmboilerplate.data.model.pokemon.PokemonDetails
import com.tcs.mvvmboilerplate.data.model.pokemonlist.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") pokemonName: String): Response<PokemonDetails>

}