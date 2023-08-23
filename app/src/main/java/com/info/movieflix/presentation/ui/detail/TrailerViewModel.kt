package com.info.movieflix.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.movieflix.common.util.Resource
import com.info.movieflix.data.repository.movieRepository
import com.info.movieflix.domain.model.state.TrailerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrailerViewModel  @Inject constructor(
    private val movieRepository: movieRepository
):ViewModel(){

    private val _trailer= MutableLiveData<TrailerUiState>()
    val trailer: LiveData<TrailerUiState> = _trailer


    fun getTrailer(id: Int) {
        viewModelScope.launch {
            movieRepository.getTrailers(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _trailer.value = TrailerUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _trailer.value = TrailerUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _trailer.value = TrailerUiState.Loading
                    }
                }
            }
        }
    }

}