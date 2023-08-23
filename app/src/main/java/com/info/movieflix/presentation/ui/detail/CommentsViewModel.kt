package com.info.movieflix.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.movieflix.common.util.Resource
import com.info.movieflix.data.repository.movieRepository
import com.info.movieflix.domain.model.state.ReviewUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CommentsViewModel@Inject constructor(
    private val movieRepository: movieRepository
) : ViewModel() {

    private val _reviews= MutableLiveData<ReviewUiState>()
    val reviews: LiveData<ReviewUiState> = _reviews


    fun getReviews(id: Int) {
        viewModelScope.launch {
            movieRepository.getReviews(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _reviews.value = ReviewUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _reviews.value = ReviewUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _reviews.value = ReviewUiState.Loading
                    }
                }
            }
        }
    }
}