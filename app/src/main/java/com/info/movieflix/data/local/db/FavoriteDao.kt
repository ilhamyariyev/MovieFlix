package com.info.androidileriders2.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteItem: Favorite)

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteItems(): LiveData<List<Favorite>>

    @Delete
    fun delete(favoriteItem: Favorite)
}