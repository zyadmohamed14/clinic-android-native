package com.careline.clinicapp.feature.auth.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.careline.clinicapp.core.common.AppFailure
import com.careline.clinicapp.core.common.Resource
import com.careline.clinicapp.feature.auth.data.model.LoginRequest
import com.careline.clinicapp.feature.auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,  // snackbar/dialog message
    val isLoggedIn: Boolean = false,
)
sealed class LoginUiEvent {
    data class ShowToast(val message: String) : LoginUiEvent()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<LoginUiEvent>()
    val event = _event.asSharedFlow()

    fun onEmailChanged(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                emailError = null
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun onLoginClicked() {
        if (!validate()) return

        val state = _uiState.value

        viewModelScope.launch {
            loginUseCase(
                LoginRequest(
                    email = state.email.trim(),
                    password = state.password
                )
            ).collect { resource ->

                when (resource) {

                    Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoggedIn = true
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false
                            )
                        }

                        _event.emit(
                            LoginUiEvent.ShowToast(
                                resource.failure.userMessage()
                            )
                        )
                    }
                }
            }
        }
    }

    private fun validate(): Boolean {
        val state = _uiState.value

        val emailError = when {
            state.email.isBlank() -> "Email is required"
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(state.email)
                .matches() -> "Invalid email format"
            else -> null
        }

        val passwordError = when {
            state.password.isBlank() -> "Password is required"
            state.password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }

        _uiState.update {
            it.copy(
                emailError = emailError,
                passwordError = passwordError
            )
        }

        return emailError == null && passwordError == null
    }
}