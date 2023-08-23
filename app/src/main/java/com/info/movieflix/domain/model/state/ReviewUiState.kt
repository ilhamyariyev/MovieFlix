package com.info.movieflix.domain.model.state

import com.info.movieflix.data.dto.ReviewsResponseModelItem

sealed class ReviewUiState{
    object Loading : ReviewUiState()
    data class Success(val data: ReviewsResponseModelItem) : ReviewUiState()
    data class Error(val message: String) : ReviewUiState()
}
