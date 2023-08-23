package com.info.movieflix.presentation.ui.seeAllMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.movieflix.common.util.Resource
import com.info.movieflix.data.repository.movieRepository
import com.info.movieflix.domain.model.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SeeAllMoviesViewModel @Inject constructor(
    val repository: movieRepository
):ViewModel(){

    private val _movieData = MutableLiveData<MovieUiState>()
    val movieData: LiveData<MovieUiState> get() = _movieData

    fun getTop10() {
        viewModelScope.launch {
            repository.getTopRatedData().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _movieData.value = MovieUiState.Success(it.data.results) }
                    is Resource.Error -> {
                        _movieData.value = MovieUiState.Error(it.exception.message.toString())
                    }
                    is Resource.Loading -> {
                        _movieData.value = MovieUiState.Loading
                    }
                }
            }
        }
    }

    fun getNewRelease() {
        viewModelScope.launch {
            repository.getPopular().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _movieData.value = MovieUiState.Success(it.data.results)
                    }
                    is Resource.Error -> {
                        _movieData.value = MovieUiState.Error(it.exception.message.toString())
                    }
                    is Resource.Loading -> {
                        _movieData.value = MovieUiState.Loading
                    }
                }
            }
        }
    }
}