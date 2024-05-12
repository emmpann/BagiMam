package com.github.emmpann.bagimam.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.core.data.PreferencesManager
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {
    fun setUserFirstTime(userFirstTime: Boolean) = viewModelScope.launch {
        preferencesManager.setUserFirstTime(userFirstTime)
    }
}