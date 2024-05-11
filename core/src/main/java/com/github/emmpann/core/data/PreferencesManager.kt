package com.github.emmpann.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.github.emmpann.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesManager(
    private val dataStore: DataStore<Preferences>
) {
    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[NAME] ?: ""
        }
    }

    fun getEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }

    fun getUserId(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[USER_ID] ?: 0
        }
    }

    fun getUserFirstTime(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[USER_FIRST_TIME] ?: true
        }
    }

    fun isUserVerified(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_VERIFIED] ?: false
        }
    }

    suspend fun setUserFirstTime(userFirstTime: Boolean) {
        dataStore.edit{ preferences ->
            preferences[USER_FIRST_TIME] = userFirstTime
        }
    }

    suspend fun setUserVerified(isVerified: Boolean) {
        dataStore.edit{ preferences ->
            preferences[IS_VERIFIED] = isVerified
        }
    }

    suspend fun setSession(user: User) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = user.id
            preferences[TOKEN_KEY] = user.token
            preferences[NAME] = user.name
            preferences[EMAIL] = user.email
            preferences[IS_VERIFIED] = user.emailVerifiedAt != null
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences[USER_ID] = 0
            preferences[TOKEN_KEY] = ""
            preferences[NAME] = ""
            preferences[EMAIL] = ""
            preferences[IS_VERIFIED] = false
        }
    }

    companion object {
        private val USER_ID = intPreferencesKey("user_id")
        private val NAME = stringPreferencesKey("name")
        private val TOKEN_KEY = stringPreferencesKey("token_key")
        private val EMAIL = stringPreferencesKey("email_key")
        private val USER_FIRST_TIME = booleanPreferencesKey("user_first_time")
        private val IS_VERIFIED = booleanPreferencesKey("is_verified")
    }
}