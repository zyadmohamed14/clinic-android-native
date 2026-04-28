package com.careline.clinicapp.feature.auth.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.careline.clinicapp.feature.auth.data.model.UserDto
import com.careline.clinicapp.feature.auth.domain.model.User
import java.time.LocalDateTime


/**
 * Converts DTOs → domain entities.
 * Extension functions keep this clean and discoverable.
 *
 * Flutter equivalent: toEntity() / fromEntity() methods
 * inside UserModel in user_model.dart
 *
 * Why extension functions instead of a class?
 * - No state needed — pure transformation.
 * - Reads naturally: userDto.toDomain()
 */
@RequiresApi(Build.VERSION_CODES.O)
fun UserDto.toDomain(token: String): User = User(
    id = id,
    name = name,
    email = email,
    phone = phone,
    avatar = avatar,
    token = token,
    emailVerifiedAt = LocalDateTime.parse(emailVerifiedAt)
)