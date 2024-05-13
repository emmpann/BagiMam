package com.github.emmpann.core.data

import androidx.lifecycle.viewModelScope
import com.github.emmpann.core.domain.model.Donatur
import com.github.emmpann.core.domain.model.Response
import com.github.emmpann.core.domain.repository.DonationRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DonationRepositoryImpl(private val db: FirebaseFirestore) : DonationRepository {

    override suspend fun sendDonation(donatur: Donatur): Flow<Response<Void?>> = flow {
        try {
            emit(Response.Loading)

            val data = hashMapOf(
                "email" to donatur.email,
                "item" to donatur.item,
                "address" to donatur.address,
            )

            val newDonatur = db.document(donatur.id).set(data).await()
            emit(Response.Success(newDonatur))
        } catch (e: Exception) {
            emit(Response.Failure(e.message ?: e.toString()))
        }
    }
}