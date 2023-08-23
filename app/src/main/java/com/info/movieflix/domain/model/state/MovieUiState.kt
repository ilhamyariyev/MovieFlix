package com.info.movieflix.domain.model.state

import com.info.movieflix.data.dto.DetailsResponseModelItem


sealed class MovieUiState {

    object Loading : MovieUiState()
    data class Success(val data: List<DetailsResponseModelItem>) : MovieUiState()
    data class Error(val message: String) : MovieUiState()
}