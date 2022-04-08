package com.tcs.mvvmboilerplate.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.mvvmboilerplate.data.model.pokemon.PokemonDetails
import com.tcs.mvvmboilerplate.data.repository.PokemonRepository
import com.tcs.mvvmboilerplate.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class DetailViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepository
) : ViewModel() {

    private val pokemonDetailPrivate = MutableLiveData<Resource<PokemonDetails>>()
    val pokemonDetail: LiveData<Resource<PokemonDetails>> get() = pokemonDetailPrivate

    fun getPokemonDetails(pokemonName: String) = viewModelScope.launch {
        pokemonRepo.getPokemonDetails(pokemonName).collect { resource ->
            pokemonDetailPrivate.value = resource
        }
    }
}