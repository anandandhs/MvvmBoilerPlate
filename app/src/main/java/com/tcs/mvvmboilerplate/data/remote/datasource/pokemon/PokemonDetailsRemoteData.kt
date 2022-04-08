package com.tcs.mvvmboilerplate.data.remote.datasource.pokemon

import com.tcs.mvvmboilerplate.data.model.pokemon.PokemonDetails
import com.tcs.mvvmboilerplate.data.remote.NetworkConnectivity
import com.tcs.mvvmboilerplate.data.remote.ServiceGenerator
import com.tcs.mvvmboilerplate.data.remote.datasource.NetworkCall
import com.tcs.mvvmboilerplate.data.remote.service.PokemonService
import com.tcs.mvvmboilerplate.utils.Resource
import javax.inject.Inject

class PokemonDetailsRemoteData @Inject
constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) :
    PokemonDetailsRemoteDataSource {


    override suspend fun getPokemonDetails(pokemonName: String): Resource<PokemonDetails> {
        val pokemonDetailsService = serviceGenerator.createService(PokemonService::class.java)
        return when (val response =
            NetworkCall.networkCallWithoutStatusCode(
                { (pokemonDetailsService::getPokemonDetails)(pokemonName) },
                networkConnectivity
            )) {
            is PokemonDetails -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorMessage = response.toString())
            }
        }
    }
}