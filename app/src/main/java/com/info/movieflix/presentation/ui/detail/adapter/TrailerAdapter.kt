package com.info.movieflix.presentation.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.info.movieflix.data.dto.TrailerResponse
import com.info.movieflix.databinding.ItemTrailerBinding
import com.info.movieflix.presentation.ui.detail.DetailFragmentDirections

class TrailerAdapter :RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>(){

    inner class TrailerViewHolder(private val binding:ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrailerResponse) {
            with(binding){
                trailer=item
                executePendingBindings()
                trailerCard.setOnClickListener {
                    Navigation.findNavController(it).navigate(DetailFragmentDirections.toyoutubeplayer(item.key))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val layout = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailerViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object GenreDiffUtilCallback : DiffUtil.ItemCallback<TrailerResponse>() {
        override fun areItemsTheSame(oldItem: TrailerResponse, newItem: TrailerResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrailerResponse, newItem: TrailerResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, GenreDiffUtilCallback)
}