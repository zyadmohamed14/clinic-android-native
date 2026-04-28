package com.careline.clinicapp.feature.auth.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.careline.clinicapp.core.ants.AppStrings

import com.careline.clinicapp.core.ui.components.AppButton
import com.careline.clinicapp.core.ui.components.AppButtonType
import com.careline.clinicapp.core.ui.components.EmailTextField
import com.careline.clinicapp.core.ui.components.PasswordTextField
import com.careline.clinicapp.feature.auth.presentation.viewmodel.LoginUiEvent
import com.careline.clinicapp.feature.auth.presentation.viewmodel.LoginViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is LoginUiEvent.ShowToast -> {
                    Log.d("LoginScreen", "ShowToast: ${event.message}")
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)  // ← correct
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CareLine",
                style = MaterialTheme.typography.displayLarge,
                // color = MaterialTheme.colorScheme.primary
            )
            EmailTextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChanged,
                isError = uiState.emailError != null,
                errorMessage = uiState.emailError,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            PasswordTextField(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChanged,
                isError = uiState.passwordError != null,
                errorMessage = uiState.passwordError,
                keyboardActions = KeyboardActions(
                    onDone = { viewModel.onLoginClicked() }
                )
            )
            AppButton(
                textId = AppStrings.AUTH_LOGIN,
                onClick = { viewModel.onLoginClicked() },
                isLoading = uiState.isLoading,
                enabled = true,
            )

// Outlined
            AppButton(
                textId =AppStrings.AUTH_CONTINUE_AS_GUEST,
                onClick = { },
                type = AppButtonType.Outlined,
                isLoading = uiState.isLoading,
                leadingIcon = Icons.Outlined.Person,
            )
        }
    }

}