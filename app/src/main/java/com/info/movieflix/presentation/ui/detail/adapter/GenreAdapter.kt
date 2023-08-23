package com.info.movieflix.presentation.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.info.movieflix.data.dto.Genre
import com.info.movieflix.databinding.ItemGenresBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    inner class GenreViewHolder(private val binding: ItemGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Genre) {
            with(binding){
                genre=item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layout = ItemGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object GenreDiffUtilCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, GenreDiffUtilCallback)


}