package com.careline.clinicapp.core.locale

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleManager {

    fun applyLocale(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        return context.createConfigurationContext(config)
    }

    fun getLocale(languageCode: String): Locale = Locale(languageCode)
}