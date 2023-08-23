package com.info.movieflix.domain.model

import android.os.Parcelable
import com.info.movieflix.common.util.MovieTypeEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieType(
    var title : String,
    var type: MovieTypeEnum
):Parcelable
