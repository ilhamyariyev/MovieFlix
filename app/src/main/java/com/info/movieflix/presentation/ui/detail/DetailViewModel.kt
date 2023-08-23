package com.info.movieflix.presentation.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.info.androidileriders2.roomDB.AppDatabase
import com.info.androidileriders2.roomDB.Favorite
import com.info.androidileriders2.roomDB.FavoriteDao
import com.info.movieflix.common.util.Resource
import com.info.movieflix.data.repository.movieRepository
import com.info.movieflix.domain.model.state.CastUiState
import com.info.movieflix.domain.model.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: movieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _detailMovies= MutableLiveData<DetailUiState>()
    val detailMovies: LiveData<DetailUiState> = _detailMovies

    private val _cast= MutableLiveData<CastUiState>()
    val cast: LiveData<CastUiState> = _cast

    private val favoriteDao: FavoriteDao
    private val favoriteItems: LiveData<List<Favorite>>


    fun getDetailMovie(id : Int) {
        viewModelScope.launch {
            movieRepository.getDetails(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _detailMovies.value = DetailUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _detailMovies.value = DetailUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _detailMovies.value = DetailUiState.Loading
                    }


                }
            }
        }
    }

    fun getCastDetail(id: Int) {
        viewModelScope.launch {
            movieRepository.getCredits(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _cast.value = CastUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _cast.value = CastUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _cast.value = CastUiState.Loading
                    }
                }
            }
        }
    }

    init {
        val database = AppDatabase.getDatabase(application)
        favoriteDao = database.favoriteDao()
        favoriteItems = favoriteDao.getAllFavoriteItems()
    }


    fun addFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.insert(favorite)
        }
    }



}