package com.github.emmpann.core.domain.repository

import com.github.emmpann.core.domain.model.Orphanage
import com.github.emmpann.core.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface OrphanageRepository {
    fun getOrphanages(): Flow<Response<List<Orphanage>>>
}