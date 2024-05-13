package com.github.emmpann.core.domain.repository

import com.github.emmpann.core.domain.model.Donatur
import com.github.emmpann.core.domain.model.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

interface DonationRepository {
    suspend fun sendDonation(donatur: Donatur): Flow<Response<Void?>>
}