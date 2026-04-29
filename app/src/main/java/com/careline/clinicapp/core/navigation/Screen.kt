package com.careline.clinicapp.core.navigation



sealed class Screen(val route: String) {

    // ── Onboarding ────────────────────────────────────────────────────────────
    data object Onboarding : Screen("onboarding")

    // ── Auth ──────────────────────────────────────────────────────────────────
    data object Login : Screen("login")
    data object Register : Screen("register")

    // ── Main (post-login) ─────────────────────────────────────────────────────
    data object Dashboard : Screen("dashboard")

    // ── Clinic Details — needs an argument ───────────────────────────────────
    data object ClinicDetails : Screen("clinic_details/{clinicId}") {
        fun createRoute(clinicId: Int) = "clinic_details/$clinicId"
        const val ARG_CLINIC_ID = "clinicId"
    }

    // ── Booking ───────────────────────────────────────────────────────────────
    data object Booking : Screen("booking/{clinicId}/{doctorId}") {
        fun createRoute(clinicId: Int, doctorId: String) =
            "booking/$clinicId/$doctorId"
        const val ARG_CLINIC_ID = "clinicId"
        const val ARG_DOCTOR_ID = "doctorId"
    }
}