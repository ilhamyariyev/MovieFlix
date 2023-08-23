package com.info.movieflix.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.movieflix.common.util.Resource
import com.info.movieflix.data.repository.movieRepository
import com.info.movieflix.domain.model.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RatingViewModel @Inject constructor(
    private val movieRepository: movieRepository,
): ViewModel() {

    private val _detailMovies= MutableLiveData<DetailUiState>()
    val detailMovies: LiveData<DetailUiState> = _detailMovies

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
}