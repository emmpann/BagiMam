package com.github.emmpann.bagimam.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.emmpann.core.data.PreferencesManager
import com.github.emmpann.core.domain.model.Donatur
import com.github.emmpann.core.domain.model.Response
import com.github.emmpann.core.domain.repository.DonationRepository
import com.github.emmpann.core.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val donationRepository: DonationRepository,
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private var _message = MutableLiveData<Event<Boolean>>()

    val message get() = _message

    fun sendDonate(donatur: Donatur) {
        viewModelScope.launch(Dispatchers.IO) {
            donationRepository.sendDonation(donatur).collect { response ->
                when (response) {
                    is Response.Success -> _message.postValue(Event(true))
                    is Response.Failure -> _message.postValue(Event(false))
                    Response.Loading -> {}
                }
            }
        }
    }

    val currentEmail: LiveData<String> = preferencesManager.getName().asLiveData()
}