package com.careline.clinicapp.core.navigation



import com.careline.clinicapp.core.datastore.AppDataStore
import javax.inject.Inject

class StartDestinationResolver @Inject constructor(
    private val dataStore: AppDataStore,
) {
    /**
     * Called once at app launch to determine which screen to show first.
     *
     * Flutter equivalent: the initialRoute + guards in AppRouter
     *
     * Logic:
     *   1. Has the user completed onboarding? No  → Onboarding
     *   2. Is there a stored auth token?       Yes → Dashboard
     *   3. Default                                 → Login
     */
    suspend fun resolve(): String {
        val onboardingDone = dataStore.isOnboardingDone()
        if (!onboardingDone) return Screen.Onboarding.route

        val token = dataStore.getAuthToken()
        return if (token != null) Screen.Dashboard.route
        else Screen.Auth.route
    }
}