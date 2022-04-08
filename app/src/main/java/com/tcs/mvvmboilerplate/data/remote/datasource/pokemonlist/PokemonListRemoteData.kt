package com.tcs.mvvmboilerplate.data.remote.datasource.pokemonlist

import com.tcs.mvvmboilerplate.data.model.pokemonlist.PokemonList
import com.tcs.mvvmboilerplate.data.remote.NetworkConnectivity
import com.tcs.mvvmboilerplate.data.remote.ServiceGenerator
import com.tcs.mvvmboilerplate.data.remote.datasource.NetworkCall.networkCallWithoutStatusCode
import com.tcs.mvvmboilerplate.data.remote.service.PokemonService
import com.tcs.mvvmboilerplate.utils.Resource
import javax.inject.Inject

class PokemonListRemoteData @Inject
constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : PokemonListRemoteDataSource {
    override suspend fun getPokemonList(): Resource<PokemonList> {
        val pokemonListService = serviceGenerator.createService(PokemonService::class.java)
        return when (val response =
            networkCallWithoutStatusCode(pokemonListService::getPokemonList, networkConnectivity)) {
            is PokemonList -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorMessage = response.toString())
            }
        }
    }

}