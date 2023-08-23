package com.info.movieflix.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.info.movieflix.common.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
) {

    suspend fun login(
        email: String,
        password: String,
    ): Flow<Resource<AuthResult>> =
        flow {
            emit(Resource.Loading)
            val user = auth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(user))
        }.catch {
            emit(Resource.Error(it as Exception))
        }


    fun register(email: String, password: String): Flow<Resource<AuthResult>> =
        flow {
            emit(Resource.Loading)
            val user = auth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(user))
        }.catch {
            emit(Resource.Error(it as Exception))
        }


    fun logOut(): Flow<Resource<Boolean>> =
        flow {
            emit(Resource.Loading)
            auth.signOut()
            emit(Resource.Success(true))
        }.catch {
            emit(Resource.Error(it as Exception))
        }


}