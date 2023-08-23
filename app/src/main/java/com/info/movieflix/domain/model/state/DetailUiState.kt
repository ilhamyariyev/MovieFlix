package com.info.movieflix.domain.model.state

import com.info.movieflix.data.dto.DetailsResponseModelItem


sealed class DetailUiState {

    object Loading : DetailUiState()
    data class Success(val data: DetailsResponseModelItem) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}