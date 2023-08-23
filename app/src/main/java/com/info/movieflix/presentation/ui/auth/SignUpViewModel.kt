package com.info.movieflix.presentation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.info.movieflix.common.util.RegisterFieldsState
import com.info.movieflix.common.util.RegisterValidation
import com.info.movieflix.common.util.User
import com.info.movieflix.common.util.validateEmail
import com.info.movieflix.common.util.validatePassword
import com.info.movieflix.data.local.sp.PrefManager
import com.info.movieflix.domain.model.state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val sp: PrefManager
) : ViewModel() {
    private val _authResult = MutableLiveData<AuthUiState>()
    val authResult: LiveData<AuthUiState> get() = _authResult

    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()



    fun createAccountWithEmailAndPassword(user: User, password: String) {
        viewModelScope.launch {
            if (checkValidation(user, password)) {
                viewModelScope.launch {
                    _authResult.value = AuthUiState.Loading
                }
                firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                    .addOnSuccessListener {
                        it.user?.let {
                            _authResult.value = AuthUiState.Success("Succesfully create user")
                            sp.saveEmail(user.email)
                            sp.savePassword(password)
                        }
                    }.addOnFailureListener {
                        _authResult.value = it.localizedMessage?.let { it1 -> AuthUiState.Error(it1) }
                    }
            } else {
                val registerFieldsState = RegisterFieldsState(
                    validateEmail(user.email),
                    validatePassword(password)
                )
                runBlocking {
                    _validation.send(registerFieldsState)
                }
            }
        }

    }
    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)

        val shouldRegister =
            emailValidation is RegisterValidation.Success &&
                    passwordValidation is RegisterValidation.Success

        return shouldRegister
    }


    fun saveEmail(email: String) {
        sp.saveEmail(email)
    }

    fun savePassword(password: String) {
        sp.savePassword(password)
    }

}



//    fun createAccountWithEmailAndPassword(user: User, password: String) {
//        viewModelScope.launch {
//            repository.register(user.email, password).collectLatest {
//                when(it){
//                    is Resource.Success -> {
//                        _authResult.value = AuthUiState.Success("Succesfully create user")
//                    }
//
//                    is Resource.Error -> {
//                        _authResult.value = AuthUiState.Error(it.exception.message.toString())
//                    }
//
//                    is Resource.Loading -> {
//                        _authResult.value = AuthUiState.Loading
//
//                    }
//                    else->{
//                        val registerFieldsState = RegisterFieldsState(
//                            validateEmail(user.email),
//                            validatePassword(password)
//                        )
//                        runBlocking {
//                            _validation.send(registerFieldsState)
//                        }
//                    }
//                }



