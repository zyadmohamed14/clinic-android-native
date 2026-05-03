package com.careline.clinicapp.core.datastore



import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.util.Locale

@Singleton
class AppStorage @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        private const val PREFS_NAME = "careline_prefs"

        // Keys
        const val KEY_LANGUAGE = "language"
        const val KEY_DARK_MODE = "dark_mode"
        const val KEY_ONBOARDING_DONE = "onboarding_done"

        // Defaults
        const val DEFAULT_LANGUAGE = "ar"

        /**
         * Called from attachBaseContext — before Hilt is ready.
         * This is the ONLY place we access SharedPreferences statically.
         */
        fun getLanguageEarly(context: Context): String =
            context
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_LANGUAGE, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // ── Language ──────────────────────────────────────────────────────────────

    fun getLanguage(): String =
        prefs.getString(KEY_LANGUAGE, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE

    fun setLanguage(code: String) {
        prefs.edit { putString(KEY_LANGUAGE, code) }
    }

    fun getLanguageFlow(): Flow<String> = prefs.observeKey(KEY_LANGUAGE, DEFAULT_LANGUAGE)

    // ── Dark Mode ─────────────────────────────────────────────────────────────

    fun isDarkMode(): Boolean = prefs.getBoolean(KEY_DARK_MODE, false)

    fun setDarkMode(isDark: Boolean) {
        prefs.edit { putBoolean(KEY_DARK_MODE, isDark) }
    }

    fun isDarkModeFlow(): Flow<Boolean> = prefs.observeKey(KEY_DARK_MODE, false)

    // ── Onboarding ────────────────────────────────────────────────────────────

    fun isOnboardingDone(): Boolean = prefs.getBoolean(KEY_ONBOARDING_DONE, false)

    fun setOnboardingDone() {
        prefs.edit { putBoolean(KEY_ONBOARDING_DONE, true) }
    }

    // ── Clear (logout) ────────────────────────────────────────────────────────

    fun clearPreferences() {
        prefs.edit {
            remove(KEY_DARK_MODE)
            remove(KEY_LANGUAGE)
            // Note: intentionally NOT clearing onboarding
        }
    }
}

// ── SharedPreferences → Flow extension ───────────────────────────────────────

/**
 * Converts a SharedPreferences key into a cold Flow.
 * Emits the current value immediately, then emits on every change.
 */
@Suppress("UNCHECKED_CAST")
private fun <T> SharedPreferences.observeKey(key: String, default: T): Flow<T> =
    callbackFlow {
        // Emit current value immediately
        trySend(getValueForKey(key, default))

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
            if (changedKey == key) {
                trySend(getValueForKey(key, default))
            }
        }

        registerOnSharedPreferenceChangeListener(listener)
        awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
    }.distinctUntilChanged()

@Suppress("UNCHECKED_CAST")
private fun <T> SharedPreferences.getValueForKey(key: String, default: T): T =
    when (default) {
        is String -> (getString(key, default) ?: default) as T
        is Boolean -> getBoolean(key, default) as T
        is Int -> getInt(key, default as Int) as T
        is Long -> getLong(key, default as Long) as T
        is Float -> getFloat(key, default as Float) as T
        else -> throw IllegalArgumentException("Unsupported type for key: $key")
    }