package com.tcs.mvvmboilerplate.ui.component.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tcs.mvvmboilerplate.data.model.pokemonlist.PokemonList
import com.tcs.mvvmboilerplate.databinding.FragmentFeedBinding
import com.tcs.mvvmboilerplate.ui.base.BaseFragment
import com.tcs.mvvmboilerplate.utils.Resource
import com.tcs.mvvmboilerplate.utils.observeAndHandle
import com.tcs.mvvmboilerplate.viewmodel.feed.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFeedBinding =
        FragmentFeedBinding::inflate

    private val viewModel: FeedViewModel by viewModels()
    private lateinit var pokemonListAdapter: PokemonListAdapter

    @Inject
    lateinit var bus: EventBus

    override fun initViewModelObservers() {
        viewModel.getPokemonList()
    }

    override fun observeViewModel() {
        observeAndHandle(viewModel.pokemonList, ::handlePokemonList)
    }

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)
        initAdapter()
    }

    private fun handlePokemonList(status: Resource<PokemonList>) {
        when (status) {
            is Resource.DataError -> {
                binding.spinner.visibility = View.GONE
                status.errorMessage?.let { Log.d("Error", it) }
            }
            is Resource.Loading -> {
                binding.spinner.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                binding.spinner.visibility = View.GONE
                status.data?.let { pokemonList ->
                    pokemonListAdapter = PokemonListAdapter(pokemonList.pokemons)
                    binding.rvPokemonList.adapter = pokemonListAdapter
                    pokemonListAdapter.onItemClick = { pokemon ->
                        bus.postSticky(SelectPokemonEvent(pokemonName = pokemon.name))
                        navigateToDetailScreen()
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.rvPokemonList.layoutManager = layoutManager
    }

    private fun navigateToDetailScreen() {
        val toDetailScreen = FeedFragmentDirections.actionFeedFragmentToDetailFragment()
        findNavController().navigate(toDetailScreen)
    }

}

