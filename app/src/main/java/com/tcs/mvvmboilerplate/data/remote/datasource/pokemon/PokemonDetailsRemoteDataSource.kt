package com.tcs.mvvmboilerplate.data.remote.datasource.pokemon

import com.tcs.mvvmboilerplate.data.model.pokemon.PokemonDetails
import com.tcs.mvvmboilerplate.utils.Resource

interface PokemonDetailsRemoteDataSource {
    suspend fun getPokemonDetails(pokemonName: String): Resource<PokemonDetails>
}