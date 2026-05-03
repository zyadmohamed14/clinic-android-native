package com.careline.clinicapp.core.datastore



import kotlinx.coroutines.flow.Flow

/**
 * Interface for all persistent preference storage.
 *
 * Flutter equivalent: SharedPrefHelper — but abstracted behind an
 * interface so the underlying implementation can be swapped
 * (e.g., in tests you inject a fake).
 *
 * Rule: ViewModels and repositories ONLY depend on this interface,
 * never on AppDataStoreImpl directly.
 */
interface AppDataStore {
    suspend fun saveAuthToken(token: String)
    suspend fun getAuthToken(): String?
    suspend fun clearAuthToken()
    suspend fun saveUserData(userJson: String)
    suspend fun getUserData(): String?
    suspend fun clearUserData()
    suspend fun setDarkMode(isDark: Boolean)
    fun isDarkModeFlow(): Flow<Boolean>
    suspend fun setLanguage(languageCode: String)
    fun getLanguageFlow(): Flow<String>
    suspend fun getLanguageOnce(): String
    suspend fun setOnboardingDone(done: Boolean)
    suspend fun isOnboardingDone(): Boolean
    suspend fun clearAll()
}