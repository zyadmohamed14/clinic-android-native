package com.careline.clinicapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.careline.clinicapp.core.navigation.NavGraph
import com.careline.clinicapp.core.theme.CarLineTheme
import com.careline.clinicapp.core.ui.components.DevToolsOverlay
import com.careline.clinicapp.ui.theme.Clinic_nativeTheme
import android.content.Context
import android.content.res.Configuration

import androidx.compose.ui.platform.LocalContext
import com.careline.clinicapp.core.api.interceptor.AuthEventBus
import jakarta.inject.Inject
import java.util.Locale

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var authEventBus: AuthEventBus
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var isDark by remember { mutableStateOf(false) }
            var isArabic by remember { mutableStateOf(true) }

            CarLineTheme(darkTheme = isDark) {

                ProvideLocale(isArabic = isArabic) {

                    DevToolsOverlay(
                        isDark = isDark,
                        isArabic = isArabic,

                        onToggleTheme = {
                            isDark = !isDark
                        },

                        onToggleLanguage = {
                            isArabic = !isArabic
                        }
                    ) {
                        NavGraph(
                            authEventBus
                        )
                    }
                }
            }
        }
    }
}


@SuppressLint("LocalContextConfigurationRead")
@Composable
fun ProvideLocale(
    isArabic: Boolean,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val locale = if (isArabic) Locale("ar") else Locale("en")

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)

    val localizedContext = context.createConfigurationContext(config)

    androidx.compose.runtime.CompositionLocalProvider(
        LocalContext provides localizedContext
    ) {
        content()
    }
}