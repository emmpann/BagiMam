package com.github.emmpann.core.data

import com.github.emmpann.core.domain.model.Response.Success
import com.github.emmpann.core.domain.model.Response.Failure
import com.github.emmpann.core.domain.model.Response.Loading
import com.github.emmpann.core.domain.repository.AuthRepository
import com.github.emmpann.core.domain.repository.AuthStateResponse
import com.github.emmpann.core.domain.repository.ReloadUserResponse
import com.github.emmpann.core.domain.repository.RevokeAccessResponse
import com.github.emmpann.core.domain.repository.SendEmailVerificationResponse
import com.github.emmpann.core.domain.repository.SendPasswordResetEmailResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl constructor(private val auth: FirebaseAuth) : AuthRepository {

    override val currentUser get() = auth.currentUser

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String,
    ) = flow {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }.onStart {
        emit(Loading)
    }.flowOn(Dispatchers.IO)

    override suspend fun sendEmailVerification(): SendEmailVerificationResponse {
        TODO("Not yet implemented")
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String,
        password: String,
    ) = flow {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }.onStart {
        emit(Loading)
    }.flowOn(Dispatchers.IO)


    override suspend fun reloadFirebaseUser(): ReloadUserResponse {
        TODO("Not yet implemented")
    }

    override suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse {
        TODO("Not yet implemented")
    }

    override fun signOut() = auth.signOut()


    override suspend fun revokeAccess(): RevokeAccessResponse {
        TODO("Not yet implemented")
    }

    override fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse {
        TODO("Not yet implemented")
    }
}