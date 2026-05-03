package com.careline.clinicapp.core.datastore

// core/storage/AuthStorage.kt


import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Stores sensitive auth data using EncryptedSharedPreferences.
 * Separated from AppStorage because encryption setup is different
 * and these values are never needed in attachBaseContext.
 */
@Singleton
class AuthStorage @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private companion object {
        const val PREFS_NAME = "careline_auth"
        const val KEY_TOKEN = "auth_token"
        const val KEY_USER_DATA = "user_data"
    }

    private val prefs: SharedPreferences by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    fun saveToken(token: String) {
        prefs.edit { putString(KEY_TOKEN, token) }
    }

    fun getUserData(): String? = prefs.getString(KEY_USER_DATA, null)

    fun saveUserData(json: String) {
        prefs.edit { putString(KEY_USER_DATA, json) }
    }

    fun clear() {
        prefs.edit { clear() }
    }
}