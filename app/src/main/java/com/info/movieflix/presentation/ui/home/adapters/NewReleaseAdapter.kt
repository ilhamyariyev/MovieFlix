package com.info.movieflix.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.info.movieflix.common.util.DiffUtilCallback
import com.info.movieflix.data.dto.DetailsResponseModelItem
import com.info.movieflix.databinding.ItemNewReleaseBinding
import com.info.movieflix.presentation.ui.home.HomeFragmentDirections


class NewReleaseAdapter  : RecyclerView.Adapter<NewReleaseAdapter.MovieHolder>() {


    inner class MovieHolder(val binding: ItemNewReleaseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailsResponseModelItem) {
            with(binding) {
                movie = item
                executePendingBindings()
                newRelease.setOnClickListener {
                    Navigation.findNavController(it).navigate(HomeFragmentDirections.todetailFragment(item.id.toString()))
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = ItemNewReleaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {

        return differ.currentList.size

    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    val differ = AsyncListDiffer(this, DiffUtilCallback)



}