package com.tcs.mvvmboilerplate.data.repository

import com.tcs.mvvmboilerplate.data.model.pokemon.PokemonDetails
import com.tcs.mvvmboilerplate.data.model.pokemonlist.PokemonList
import com.tcs.mvvmboilerplate.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepositorySource {
    suspend fun getPokemonList(): Flow<Resource<PokemonList>>
    suspend fun getPokemonDetails(pokemonName: String):Flow<Resource<PokemonDetails>>
}