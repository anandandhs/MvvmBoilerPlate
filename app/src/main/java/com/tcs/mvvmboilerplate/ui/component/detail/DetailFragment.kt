package com.tcs.mvvmboilerplate.ui.component.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.RequestManager
import com.tcs.mvvmboilerplate.R
import com.tcs.mvvmboilerplate.data.model.pokemon.PokemonDetails
import com.tcs.mvvmboilerplate.databinding.FragmentDetailBinding
import com.tcs.mvvmboilerplate.ui.base.BaseFragment
import com.tcs.mvvmboilerplate.ui.component.feed.SelectPokemonEvent
import com.tcs.mvvmboilerplate.utils.Resource
import com.tcs.mvvmboilerplate.utils.observeAndHandle
import com.tcs.mvvmboilerplate.viewmodel.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding =
        FragmentDetailBinding::inflate

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var bus: EventBus

    @Inject
    lateinit var glide: RequestManager

    override fun observeViewModel() {}

    override fun initViewModelObservers() {
        observeAndHandle(viewModel.pokemonDetail, ::handlePokemonDetails)
    }

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)
        bus.register(this)
    }

    private fun handlePokemonDetails(status: Resource<PokemonDetails>) {
        when (status) {
            is Resource.DataError -> {
                binding.spinner.visibility = View.GONE
            }
            is Resource.Loading -> {
                binding.spinner.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                binding.spinner.visibility = View.GONE
                status.data?.let { pokemonDetails ->
                    binding.apply {
                        glide.load(pokemonDetails.sprites.front_default)
                            .centerCrop()
                            .placeholder(R.drawable.placeholder)
                            .into(binding.ivAvatar)
                        tvId.text = pokemonDetails.id.toString()
                        tvName.text = pokemonDetails.name
                        tvHeight.text = pokemonDetails.height.toString()
                        tvWeight.text = pokemonDetails.weight.toString()
                        tvType.text = pokemonDetails.types[0].type.name
                    }
                }
            }
        }
    }

    override fun onStop() {
        bus.unregister(this)
        super.onStop()
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onSelectPokemonEvent(event: SelectPokemonEvent) {
        viewModel.getPokemonDetails(
            EventBus.getDefault().removeStickyEvent(
                SelectPokemonEvent::class.java
            ).pokemonName
        )
    }

}