package com.info.movieflix.presentation.ui.myList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.info.androidileriders2.roomDB.AppDatabase
import com.info.androidileriders2.roomDB.Favorite
import com.info.androidileriders2.roomDB.FavoriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyListViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteDao: FavoriteDao
    val favoriteItems: LiveData<List<Favorite>>

    init {
        val database = AppDatabase.getDatabase(application)
        favoriteDao = database.favoriteDao()
        favoriteItems = favoriteDao.getAllFavoriteItems()
    }

    fun getFavoriteItemsLiveData(): LiveData<List<Favorite>> {
        return favoriteItems
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.delete(favorite)
        }
    }


}