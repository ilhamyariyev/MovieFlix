package com.info.movieflix.presentation.ui.myList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.info.androidileriders2.roomDB.Favorite
import com.info.movieflix.common.util.ImageTypeEnum
import com.info.movieflix.common.util.loadUrl
import com.info.movieflix.databinding.ItemFavoriteBinding
import com.info.movieflix.presentation.ui.myList.MyListFragmentDirections

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite) {
            with(binding){
                tvTitle.text = favorite.title
                ivMovie.loadUrl(favorite.image,ImageTypeEnum.MOVIE_IMAGE)
                tvRating.text=favorite.vote.toString()


                favoriteCard.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(MyListFragmentDirections.todetailFragment(favorite.id.toString()))
                }
            }

        }
    }

    object diffUtilFavorite : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtilFavorite)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layout =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FavoriteViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteProduct = differ.currentList[position]

        holder.bind(favoriteProduct)

        holder.binding.delete.setOnClickListener {
            onDeleteClick?.invoke(favoriteProduct)
        }
    }


    var onDeleteClick: ((Favorite) -> Unit)? = null
}