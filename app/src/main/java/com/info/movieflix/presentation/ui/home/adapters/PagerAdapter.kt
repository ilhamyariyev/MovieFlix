package com.info.movieflix.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.info.movieflix.common.util.DiffUtilCallback
import com.info.movieflix.data.dto.DetailsResponseModelItem
import com.info.movieflix.databinding.ItemPagingBinding
import com.info.movieflix.presentation.ui.home.HomeFragmentDirections

class PagerAdapter : RecyclerView.Adapter<PagerAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemPagingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailsResponseModelItem) {
            with(binding) {
                movie=item
                executePendingBindings()
                pager.setOnClickListener {
                    Navigation.findNavController(it).navigate(HomeFragmentDirections.todetailFragment(item.id.toString()))
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemPagingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
    }


    val differ = AsyncListDiffer(this, DiffUtilCallback)


}