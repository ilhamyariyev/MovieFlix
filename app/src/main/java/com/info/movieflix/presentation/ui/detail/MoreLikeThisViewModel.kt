package com.info.movieflix.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.movieflix.common.util.Resource
import com.info.movieflix.data.repository.movieRepository
import com.info.movieflix.domain.model.state.RecommendationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoreLikeThisViewModel @Inject constructor(
    private val movieRepository: movieRepository
) :ViewModel(){
    private val _recommendation= MutableLiveData<RecommendationUiState>()
    val recommendation: LiveData<RecommendationUiState> = _recommendation


    fun getMoreLikeThis(id: Int) {
        viewModelScope.launch {
            movieRepository.getRecommendations(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _recommendation.value = RecommendationUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _recommendation.value = RecommendationUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _recommendation.value = RecommendationUiState.Loading
                    }
                }
            }
        }
    }

}