package com.github.emmpann.core.data

import android.util.Log
import com.github.emmpann.core.domain.model.Orphanage
import com.github.emmpann.core.domain.model.Response
import com.github.emmpann.core.domain.repository.OrphanageRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class OrphanageRepositoryImpl(private val db: FirebaseFirestore) : OrphanageRepository {
    private val collection = db.collection("orphanages")

    override fun getOrphanages(): Flow<Response<List<Orphanage>>> = callbackFlow {
        val snapshotListener = collection.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val books = snapshot.toObjects(Orphanage::class.java)
                Log.d("OrphanageRepository", "get panti: ${books.size}")
                Response.Success(books)
            } else {
                Response.Failure(e?.message ?: e.toString())
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}