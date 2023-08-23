package com.info.movieflix.domain.model.state

import com.info.movieflix.data.dto.RecommendationResponseModelItem

sealed class RecommendationUiState{
    object Loading : RecommendationUiState()
    data class Success(val data: RecommendationResponseModelItem) : RecommendationUiState()
    data class Error(val message: String) : RecommendationUiState()
}
