package com.github.emmpann.bagimam.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.core.domain.repository.AuthRepository
import com.github.emmpann.core.domain.repository.SignUpResponse
import kotlinx.coroutines.launch

class RegisterViewModel(
    private var authRepository: AuthRepository,
) : ViewModel() {

    private var _signUpResponse = MutableLiveData<SignUpResponse>()

    val signUpResponse: LiveData<SignUpResponse> get() = _signUpResponse

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        authRepository.firebaseSignUpWithEmailAndPassword(email, password).collect {
            _signUpResponse.value = it
        }
    }
}