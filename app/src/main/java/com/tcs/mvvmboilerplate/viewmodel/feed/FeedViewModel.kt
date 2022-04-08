package com.tcs.mvvmboilerplate.viewmodel.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.mvvmboilerplate.data.model.pokemonlist.PokemonList
import com.tcs.mvvmboilerplate.data.repository.PokemonRepository
import com.tcs.mvvmboilerplate.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class FeedViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepository
) : ViewModel() {

    private val pokemonListPrivate = MutableLiveData<Resource<PokemonList>>()
    val pokemonList: LiveData<Resource<PokemonList>> get() = pokemonListPrivate

    fun getPokemonList() = viewModelScope.launch {
        pokemonRepo.getPokemonList().collect { resource ->
            pokemonListPrivate.value = resource
        }
    }
}
