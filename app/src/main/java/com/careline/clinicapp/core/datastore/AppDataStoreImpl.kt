package com.careline.clinicapp.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.careline.clinicapp.core.constants.AppConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AppConstants.DATASTORE_NAME)
// core/datastore/AppDataStoreImpl.kt — now just a thin delegator
@Singleton
class AppDataStoreImpl @Inject constructor(
    private val appStorage: AppStorage,
    private val authStorage: AuthStorage,
) : AppDataStore {

    override suspend fun saveAuthToken(token: String) = authStorage.saveToken(token)
    override suspend fun getAuthToken(): String? = authStorage.getToken()
    override suspend fun clearAuthToken() = authStorage.clear()

    override suspend fun saveUserData(userJson: String) = authStorage.saveUserData(userJson)
    override suspend fun getUserData(): String? = authStorage.getUserData()
    override suspend fun clearUserData() = authStorage.clear()

    override suspend fun setDarkMode(isDark: Boolean) = appStorage.setDarkMode(isDark)
    override fun isDarkModeFlow(): Flow<Boolean> = appStorage.isDarkModeFlow()

    override suspend fun setLanguage(languageCode: String) = appStorage.setLanguage(languageCode)
    override fun getLanguageFlow(): Flow<String> = appStorage.getLanguageFlow()
    override suspend fun getLanguageOnce(): String = appStorage.getLanguage()

    override suspend fun setOnboardingDone(done: Boolean) = appStorage.setOnboardingDone()
    override suspend fun isOnboardingDone(): Boolean = appStorage.isOnboardingDone()

    override suspend fun clearAll() {
        authStorage.clear()
        appStorage.clearPreferences()
    }
}