package com.info.movieflix.presentation.ui.welcomeSlider

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.info.movieflix.databinding.ItemSliderPagesBinding


class PagesAdapter(private val pageList:List<pages>): RecyclerView.Adapter<PagesAdapter.pagesViewHolder>() {

    inner class pagesViewHolder(private val itemPagesBinding: ItemSliderPagesBinding) : RecyclerView.ViewHolder(itemPagesBinding.root){
        fun bindItem(pages: pages){
            itemPagesBinding.imageView.setImageResource(pages.image)
            itemPagesBinding.textView.text=pages.inf

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pagesViewHolder {
        return pagesViewHolder(
            ItemSliderPagesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return pageList.size
    }

    override fun onBindViewHolder(holder: pagesViewHolder, position: Int) {
        holder.bindItem(pageList[position])
    }


}