package com.tcs.mvvmboilerplate.ui.component.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tcs.mvvmboilerplate.data.model.pokemonlist.Pokemon
import com.tcs.mvvmboilerplate.databinding.PokemonListItemBinding


class PokemonListAdapter(
    var pokemonList: List<Pokemon>,
) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    var onItemClick: ((Pokemon) -> Unit)? = null

    inner class ViewHolder(val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(pokemonList[adapterPosition])
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(pokemonList[position]) {
                binding.tvPokeName.text = this.name
            }
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}
