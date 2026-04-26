package com.careline.clinicapp.core.constants



/**
 * Central location for all magic strings and numbers.
 *
 * Flutter equivalent: app_constans.dart (AppConstants + ErrorMessages classes)
 *
 * BASE_URL is NOT here — it comes from BuildConfig.BASE_URL
 * which is set per build flavor in build.gradle.kts.
 * This means dev/staging/production each get their own URL automatically.
 */
object AppConstants {

    // ── DataStore keys ────────────────────────────────────────────────────────
    const val DATASTORE_NAME = "careline_prefs"
    const val KEY_AUTH_TOKEN = "auth_token"
    const val KEY_USER_DATA = "user_data"
    const val KEY_LANGUAGE = "language"
    const val KEY_IS_DARK_MODE = "is_dark_mode"
    const val KEY_ONBOARDING_DONE = "onboarding_done"

    // ── Network timeouts (milliseconds) ───────────────────────────────────────

    const val CONNECT_TIMEOUT_SECONDS = 30L
    const val READ_TIMEOUT_SECONDS = 30L
    const val WRITE_TIMEOUT_SECONDS = 30L

    // ── Pagination ────────────────────────────────────────────────────────────

    const val DEFAULT_PAGE_SIZE = 10
    const val INITIAL_PAGE = 1

    // ── Validation ────────────────────────────────────────────────────────────
    const val MIN_PASSWORD_LENGTH = 8
    const val MAX_PASSWORD_LENGTH = 50
}

/** All API endpoint paths — mirrors endpoints.dart exactly */
object Endpoints {
    // Auth
    const val LOGIN = "auth/login"
    const val REGISTER = "auth/register"
    const val LOGOUT = "auth/logout"

    // Clinics
    const val ALL_CLINICS = "clinicals"
    const val LATEST_CLINICS = "clinicals/latest"
    const val CLINIC_DETAIL = "clinicals/{id}"

    // Favorites
    const val TOGGLE_FAVORITE = "favorites/toggle"
    const val GET_FAVORITES = "favorites"

    // Bookings
    const val BOOK_APPOINTMENT = "bookings"
    const val GET_BOOKINGS = "bookings"
    const val CANCEL_BOOKING = "bookings/{id}/cancel"

    // Profile
    const val PROFILE = "profile"
    const val UPDATE_PROFILE = "profile/update"
}