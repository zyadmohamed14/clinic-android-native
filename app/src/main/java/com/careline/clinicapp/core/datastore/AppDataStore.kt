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

    // ── Auth ──────────────────────────────────────────────────────────────────
    suspend fun saveAuthToken(token: String)
    suspend fun getAuthToken(): String?
    suspend fun clearAuthToken()

    // ── User data ─────────────────────────────────────────────────────────────
    suspend fun saveUserData(userJson: String)
    suspend fun getUserData(): String?
    suspend fun clearUserData()

    // ── App preferences ───────────────────────────────────────────────────────
    suspend fun setDarkMode(isDark: Boolean)
    fun isDarkModeFlow(): Flow<Boolean>

    suspend fun setLanguage(languageCode: String)
    fun getLanguageFlow(): Flow<String>

    suspend fun setOnboardingDone(done: Boolean)
    suspend fun isOnboardingDone(): Boolean

    // ── Clear everything (logout) ─────────────────────────────────────────────
    suspend fun clearAll()
}