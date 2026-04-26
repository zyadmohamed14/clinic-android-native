package com.careline.clinicapp.core.navigation



import androidx.compose.material3.Text
import androidx.compose.runtime.Composable




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.careline.clinicapp.core.api.interceptor.AuthEvent
import com.careline.clinicapp.core.api.interceptor.AuthEventBus
import com.careline.clinicapp.core.theme.extraColors

/**
 * Temporary theme preview — replaced with real navigation in Step 5.
 */
@Composable
fun NavGraph(
    authEventBus: AuthEventBus,
) {
    // Listen for 401 events from the network layer
    LaunchedEffect(Unit) {
        authEventBus.events.collect { event ->
            when (event) {
                is AuthEvent.Unauthorized -> {
                    // TODO: navigate to login in Step 6 when
                    // NavController is set up
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "CareLine",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Theme is working ✅",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Success color check",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.extraColors.successFill,
        )
    }
}