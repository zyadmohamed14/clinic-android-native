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
@Singleton
class AppDataStoreImpl  @Inject constructor(
    @ApplicationContext private val context: Context,
) : AppDataStore{
    private companion object {
        val KEY_AUTH_TOKEN = stringPreferencesKey(AppConstants.KEY_AUTH_TOKEN)
        val KEY_USER_DATA = stringPreferencesKey(AppConstants.KEY_USER_DATA)
        val KEY_IS_DARK_MODE = booleanPreferencesKey(AppConstants.KEY_IS_DARK_MODE)
        val KEY_LANGUAGE = stringPreferencesKey(AppConstants.KEY_LANGUAGE)
        val KEY_ONBOARDING_DONE = booleanPreferencesKey(AppConstants.KEY_ONBOARDING_DONE)
    }
    override suspend fun saveAuthToken(token: String) {
       context.dataStore.edit { prefs ->
           prefs[KEY_AUTH_TOKEN] = token
       }
    }

    override suspend fun getAuthToken(): String? {
        return context.dataStore.data
            .firstOrNull()
            ?.get(KEY_AUTH_TOKEN)
    }

    override suspend fun clearAuthToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(KEY_AUTH_TOKEN)
        }
    }

    override suspend fun saveUserData(userJson: String) {
       context.dataStore.edit { prefs ->
           prefs[KEY_USER_DATA] = userJson
       }
    }

    override suspend fun getUserData(): String? {
        return context.dataStore.data
            .firstOrNull()
            ?.get(KEY_USER_DATA)
    }


    override suspend fun clearUserData() {
        context.dataStore.edit { prefs ->
            prefs.remove(KEY_USER_DATA)
        }
    }

    override suspend fun getLanguageOnce(): String {
        return context.dataStore.data
            .firstOrNull()
            ?.get(KEY_LANGUAGE) ?: "ar"
    }
    override suspend fun setDarkMode(isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IS_DARK_MODE] = isDark
        }
    }

    override fun isDarkModeFlow(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[KEY_IS_DARK_MODE] ?: false
        }
    }

    override suspend fun setLanguage(languageCode: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LANGUAGE] = languageCode
        }
    }

    override fun getLanguageFlow(): Flow<String> {
        return context.dataStore.data.map { prefs ->
            prefs[KEY_LANGUAGE] ?: "ar"   // Default to Arabic
        }
    }


    override suspend fun setOnboardingDone(done: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_ONBOARDING_DONE] = done
        }
    }

    override suspend fun isOnboardingDone(): Boolean {
        return context.dataStore.data
            .firstOrNull()
            ?.get(KEY_ONBOARDING_DONE) ?: false
    }

    override suspend fun clearAll() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}