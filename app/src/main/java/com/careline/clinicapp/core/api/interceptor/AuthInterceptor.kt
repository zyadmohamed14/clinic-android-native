package com.careline.clinicapp.core.api.interceptor


import com.careline.clinicapp.core.datastore.AppDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Attaches the JWT token to every outgoing request as a Bearer header.
 *
 * Flutter equivalent: the onRequest interceptor in dio_client.dart:
 *   options.headers['Authorization'] = 'Bearer YOUR_TOKEN';
 *
 * OkHttp interceptors are synchronous by design, but our token is stored
 * in DataStore which is async (suspend). We use runBlocking here — this
 * is one of the few acceptable uses of runBlocking because:
 *   1. We're already on OkHttp's background IO thread, not the main thread
 *   2. The operation is a fast local read (DataStore → disk cache)
 *   3. The network call can't proceed without the token anyway
 */
class AuthInterceptor @Inject constructor(
    private val dataStore: AppDataStore,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { dataStore.getAuthToken() }

        val request = if (token != null) {
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}