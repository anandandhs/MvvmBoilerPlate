package com.tcs.mvvmboilerplate.data.repository

import com.tcs.mvvmboilerplate.data.model.pokemon.PokemonDetails
import com.tcs.mvvmboilerplate.data.model.pokemonlist.PokemonList
import com.tcs.mvvmboilerplate.data.remote.datasource.pokemon.PokemonDetailsRemoteData
import com.tcs.mvvmboilerplate.data.remote.datasource.pokemonlist.PokemonListRemoteData
import com.tcs.mvvmboilerplate.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class PokemonRepository @Inject constructor(
    private val pokemonListRemoteData: PokemonListRemoteData,
    private val pokemonDetailsRemoteData: PokemonDetailsRemoteData,
    private val ioDispatcher: CoroutineContext
) : PokemonRepositorySource {
    override suspend fun getPokemonList(): Flow<Resource<PokemonList>> {
        return flow {
            emit(pokemonListRemoteData.getPokemonList())
        }.flowOn(ioDispatcher)
    }

    override suspend fun getPokemonDetails(pokemonName: String): Flow<Resource<PokemonDetails>> {
        return flow {
            emit(pokemonDetailsRemoteData.getPokemonDetails(pokemonName))
        }.flowOn(ioDispatcher)
    }


}
