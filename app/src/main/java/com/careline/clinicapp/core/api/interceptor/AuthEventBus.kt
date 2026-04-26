package com.careline.clinicapp.core.api.interceptor

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A singleton event bus that the UnauthorizedInterceptor uses to signal
 * a 401 response — and that NavGraph listens to for redirecting to login.
 *
 * Why not navigate directly from the interceptor?
 * OkHttp interceptors run on background threads. Navigation in Compose
 * must happen on the main thread. SharedFlow safely crosses that boundary.
 *
 * Flutter equivalent:
 * In dio_client.dart the onError interceptor checked statusCode == 401
 * and navigated via AppRouter.navigatorKey. We use the same pattern but
 * with Kotlin Flow instead of a GlobalKey.
 *
 * Flow:
 *   UnauthorizedInterceptor.intercept()
 *       → authEventBus.emitUnauthorized()
 *           → NavGraph collects the event
 *               → navigates to Login, clears back stack
 */
@Singleton
class AuthEventBus @Inject constructor() {

    private val _events = MutableSharedFlow<AuthEvent>(extraBufferCapacity = 1)
    val events: SharedFlow<AuthEvent> = _events.asSharedFlow()

    suspend fun emitUnauthorized() {
        _events.emit(AuthEvent.Unauthorized)
    }
}

sealed class AuthEvent {
    /** Fired when any API call returns HTTP 401 */
    data object Unauthorized : AuthEvent()
}