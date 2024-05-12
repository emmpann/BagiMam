package com.github.emmpann.bagimam.profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.core.data.PreferencesManager
import kotlinx.coroutines.launch

class ProfileViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            preferencesManager.clearSession()
            // Navigate to login screen
        }
    }
}

