package com.github.emmpann.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.github.emmpann.core.data.AuthRepositoryImpl
import com.github.emmpann.core.data.OrphanageRepositoryImpl
import com.github.emmpann.core.data.PreferencesManager
import com.github.emmpann.core.domain.repository.AuthRepository
import com.github.emmpann.core.domain.repository.OrphanageRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val Context.dataStore by preferencesDataStore("preferences")

val repositoryModule = module {
    factory { Firebase.auth }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}

val preferencesManager = module {
    single { PreferencesManager(androidContext().dataStore) }
}

val db = module {
    factory { Firebase.firestore }
    single<OrphanageRepository> {
        OrphanageRepositoryImpl(get())
    }
}