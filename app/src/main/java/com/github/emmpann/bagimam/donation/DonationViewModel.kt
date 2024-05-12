package com.github.emmpann.bagimam.donation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.core.domain.model.Orphanage
import com.github.emmpann.core.domain.model.Response
import com.github.emmpann.core.domain.repository.OrphanageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DonationViewModel(
    private val repository: OrphanageRepository
) : ViewModel() {
    private val _orphanageState = MutableLiveData<Response<List<Orphanage>>>()

    val orphanageState: LiveData<Response<List<Orphanage>>> get() = _orphanageState

    init {
        getOrphanages()
    }

    private fun getOrphanages() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getOrphanages().collect {
                _orphanageState.postValue(it)
            }
        }
    }
}