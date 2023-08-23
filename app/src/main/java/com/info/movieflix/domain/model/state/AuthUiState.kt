package com.info.movieflix.domain.model.state


sealed class AuthUiState {

    object Loading : AuthUiState()
    data class Success(val message: String) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}