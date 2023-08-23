package com.info.movieflix.domain.model.state

import com.info.movieflix.data.dto.TrailerResponseModelItem

sealed class TrailerUiState{
    object Loading : TrailerUiState()
    data class Success(val data: TrailerResponseModelItem) : TrailerUiState()
    data class Error(val message: String) : TrailerUiState()
}
