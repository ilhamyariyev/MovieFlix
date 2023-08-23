package com.info.movieflix.common.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter


object BindingAdapter{
    @BindingAdapter("load_image_url")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            imageView.loadUrl(imageUrl, ImageTypeEnum.MOVIE_IMAGE)

        }
    }

    @BindingAdapter("load_youtube")
    @JvmStatic
    fun loadYoutube(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            imageView.loadUrl(imageUrl, ImageTypeEnum.YOUTUBE_IMAGE)
        }
    }

    @BindingAdapter("load_user")
    @JvmStatic
    fun loadUserImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            imageView.loadUrl(imageUrl.removePrefix("/"), ImageTypeEnum.USER_IMAGE)
        }
    }
}
