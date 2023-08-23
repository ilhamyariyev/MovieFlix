package com.info.movieflix.data.dto


import com.google.gson.annotations.SerializedName

data class TrailerResponseModelItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<TrailerResponse>
)