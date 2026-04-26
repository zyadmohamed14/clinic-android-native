package com.careline.clinicapp.core.api.interceptor




import com.careline.clinicapp.core.datastore.AppDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Watches every response. When it sees HTTP 401:
 *   1. Clears the stored token (it's no longer valid)
 *   2. Emits an Unauthorized event via AuthEventBus
 *   3. NavGraph (on main thread) receives the event and navigates to Login
 *
 * Flutter equivalent: the onError interceptor in dio_client.dart:
 *   if (error.response?.statusCode == 401) { navigate to login }
 *
 * This runs AFTER AuthInterceptor in the OkHttp chain.
 */
class UnauthorizedInterceptor @Inject constructor(
    private val dataStore: AppDataStore,
    private val authEventBus: AuthEventBus,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code == 401) {
            runBlocking {
                // Clear the expired/invalid token
                dataStore.clearAuthToken()
                dataStore.clearUserData()

                // Signal the UI layer to navigate to login
                authEventBus.emitUnauthorized()
            }
        }

        return response
    }
}