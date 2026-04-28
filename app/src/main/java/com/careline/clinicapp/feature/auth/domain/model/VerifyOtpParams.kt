package com.careline.clinicapp.feature.auth.domain.model

data class VerifyOtpParams(
    val email: String,
    val otp: String
)