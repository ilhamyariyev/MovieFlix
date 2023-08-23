package com.info.movieflix.presentation.ui.home

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
class HomeViewModel @Inject constructor(
    private val movieRepository: movieRepository
):ViewModel(){

    private val _nowPlayingMovies = MutableLiveData<MovieUiState>()
    val nowPlayingMovies: LiveData<MovieUiState> = _nowPlayingMovies

    private val _top10Movies = MutableLiveData<MovieUiState>()
    val topRatedMovies: LiveData<MovieUiState> get() = _top10Movies

    private val _upComingMovies = MutableLiveData<MovieUiState>()
    val upComingMovies: LiveData<MovieUiState> = _upComingMovies


    init {
        getNewReleasesMovie()
        getTop10Movies()
        getNowPlayingMovies()
    }

    private fun getNewReleasesMovie() {
        viewModelScope.launch {
            movieRepository.getPopular().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _upComingMovies.value = MovieUiState.Success(it.data.results)

                    }

                    is Resource.Error -> {
                        _upComingMovies.value = MovieUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _upComingMovies.value = MovieUiState.Loading
                    }
                }
            }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            movieRepository.getNowPlayingData().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _nowPlayingMovies.value = MovieUiState.Success(it.data.results)
                    }

                    is Resource.Error -> {
                        _nowPlayingMovies.value = MovieUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _nowPlayingMovies.value = MovieUiState.Loading
                    }
                }
            }
        }
    }


    private fun getTop10Movies() {
        viewModelScope.launch {
            movieRepository.getTopRatedData().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _top10Movies.value = MovieUiState.Success(it.data.results)
                    }

                    is Resource.Error -> {
                        _top10Movies.value = MovieUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _top10Movies.value = MovieUiState.Loading
                    }
                }
            }
        }
    }



}