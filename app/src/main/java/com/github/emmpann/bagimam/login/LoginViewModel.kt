package com.github.emmpann.bagimam.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.core.data.PreferencesManager
import com.github.emmpann.core.di.preferencesManager
import com.github.emmpann.core.domain.model.User
import com.github.emmpann.core.domain.repository.AuthRepository
import com.github.emmpann.core.domain.repository.SignInResponse
import kotlinx.coroutines.launch

class LoginViewModel(
    private var authRepository: AuthRepository,
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private var _signInResponse = MutableLiveData<SignInResponse>()

    val signInResponse: LiveData<SignInResponse> get() = _signInResponse

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        authRepository.firebaseSignInWithEmailAndPassword(email, password).collect {
            _signInResponse.value = it
        }
    }

    fun setSession(user: User) = viewModelScope.launch { preferencesManager.setSession(user) }
}