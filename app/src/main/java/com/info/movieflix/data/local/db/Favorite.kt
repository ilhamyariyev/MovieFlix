package com.info.androidileriders2.roomDB

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_movies")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val image: String,
    val vote: Double
) : Parcelable
