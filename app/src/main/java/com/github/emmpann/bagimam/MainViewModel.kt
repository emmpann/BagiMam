package com.github.emmpann.bagimam

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.emmpann.core.data.PreferencesManager

class MainViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    val token: LiveData<String> = preferencesManager.getToken().asLiveData()

    val userFirstTime: LiveData<Boolean> = preferencesManager.getUserFirstTime().asLiveData()
}