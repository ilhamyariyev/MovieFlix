package com.info.movieflix.domain.model.state

import com.info.movieflix.data.dto.CastingResponseModelItem

sealed class CastUiState{
    object Loading : CastUiState()
    data class Success(val data: CastingResponseModelItem) : CastUiState()
    data class Error(val message: String) : CastUiState()
}
