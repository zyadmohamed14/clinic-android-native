package com.careline.clinicapp.core.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.careline.clinicapp.core.api.interceptor.AuthEvent
import com.careline.clinicapp.core.api.interceptor.AuthEventBus
import com.careline.clinicapp.feature.auth.presentation.screen.AuthScreen
import com.careline.clinicapp.feature.onboarding.OnboardingScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authEventBus: AuthEventBus,
    startDestination: String,
) {
    // ── Wire 401 events to navigation ─────────────────────────────────────────
    // This is the Android equivalent of Flutter's:
    //   if (error.response?.statusCode == 401) { AppRouter.navigatorKey... }
    // We use LaunchedEffect so it only runs once and respects the Composable
    // lifecycle. The event travels from OkHttp (background thread) → here
    // (main thread) via AuthEventBus SharedFlow.
    LaunchedEffect(Unit) {
        authEventBus.events.collect { event ->
            when (event) {
                AuthEvent.Unauthorized -> {
                    navController.navigate(Screen.Auth.route) {
                        // Clear the entire back stack so the user can't
                        // press Back to return to an authenticated screen.
                        // Flutter equivalent: Navigator.pushAndRemoveUntil()
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        // ── Onboarding ────────────────────────────────────────────────────────
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )

        }

        // ── Auth ──────────────────────────────────────────────────────────────
        composable(Screen.Auth.route) {
            AuthScreen()
        }

        // ── Dashboard ─────────────────────────────────────────────────────────
        composable(Screen.Dashboard.route) {
            // DashboardScreen(navController = navController)
            // Placeholder until Feature 3
            androidx.compose.material3.Text("Dashboard Screen")
        }

        // ── Clinic Details — with argument ────────────────────────────────────
        composable(
            route = Screen.ClinicDetails.route,
            arguments = listOf(
                navArgument(Screen.ClinicDetails.ARG_CLINIC_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val clinicId = backStackEntry.arguments
                ?.getInt(Screen.ClinicDetails.ARG_CLINIC_ID) ?: return@composable

            // ClinicDetailsScreen(clinicId = clinicId)
            androidx.compose.material3.Text("Clinic Details: $clinicId")
        }

        // ── Booking ───────────────────────────────────────────────────────────
        composable(
            route = Screen.Booking.route,
            arguments = listOf(
                navArgument(Screen.Booking.ARG_CLINIC_ID) {
                    type = NavType.IntType
                },
                navArgument(Screen.Booking.ARG_DOCTOR_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val clinicId = backStackEntry.arguments
                ?.getInt(Screen.Booking.ARG_CLINIC_ID) ?: return@composable
            val doctorId = backStackEntry.arguments
                ?.getString(Screen.Booking.ARG_DOCTOR_ID) ?: return@composable

            // BookingScreen(clinicId = clinicId, doctorId = doctorId)
            androidx.compose.material3.Text("Booking: $clinicId / $doctorId")
        }
    }
}


