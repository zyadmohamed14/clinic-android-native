package com.careline.clinicapp.feature.auth.domain.model

import java.time.LocalDateTime

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String? = null,
    val avatar: String? = null,
    val token: String,
    val emailVerifiedAt: LocalDateTime? = null
) {
    fun copyWith(
        id: Int? = null,
        name: String? = null,
        email: String? = null,
        phone: String? = null,
        avatar: String? = null,
        token: String? = null,
        emailVerifiedAt: LocalDateTime? = null
    ): User {
        return User(
            id = id ?: this.id,
            name = name ?: this.name,
            email = email ?: this.email,
            phone = phone ?: this.phone,
            avatar = avatar ?: this.avatar,
            token = token ?: this.token,
            emailVerifiedAt = emailVerifiedAt ?: this.emailVerifiedAt
        )
    }
}