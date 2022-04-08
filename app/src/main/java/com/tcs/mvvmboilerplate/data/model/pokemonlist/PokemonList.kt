package com.tcs.mvvmboilerplate.data.model.pokemonlist

import com.google.gson.annotations.SerializedName
import com.tcs.mvvmboilerplate.data.model.pokemonlist.Pokemon

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    @SerializedName("results")
    val pokemons: List<Pokemon>
)