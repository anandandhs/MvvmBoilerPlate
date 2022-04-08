package com.tcs.mvvmboilerplate.data.remote.datasource.pokemonlist

import com.tcs.mvvmboilerplate.data.model.pokemonlist.PokemonList
import com.tcs.mvvmboilerplate.utils.Resource

interface PokemonListRemoteDataSource {
    suspend fun getPokemonList(): Resource<PokemonList>
}