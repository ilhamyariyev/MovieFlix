package com.info.movieflix.common.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.info.movieflix.R
import com.info.movieflix.common.util.Constants.BASE_URL_IMAGE
import com.info.movieflix.common.util.Constants.BASE_URL_IMAGE_YOUTUBE
import com.info.movieflix.common.util.Constants.YOUTUBE_QUALITY

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadUrl(url: String, imageTypeEnum: ImageTypeEnum) {
    val options = RequestOptions().centerCrop().placeholder(R.drawable.gray_placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .dontAnimate()
        .dontTransform()

    val imageUrl = when (imageTypeEnum) {
        ImageTypeEnum.MOVIE_IMAGE -> BASE_URL_IMAGE + url
        ImageTypeEnum.YOUTUBE_IMAGE -> BASE_URL_IMAGE_YOUTUBE + url + YOUTUBE_QUALITY
        ImageTypeEnum.USER_IMAGE -> url
    }

    Glide.with(this).load(imageUrl).apply(options).into(this)

}





