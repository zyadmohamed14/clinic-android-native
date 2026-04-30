package com.careline.clinicapp.core.locale

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleManager {

    fun applyLocale(context: Context, languageCode: String): Context {
        // Map language code to the correct Locale
        // "ar" → Locale("ar", "EG") to match values-ar-rEG folder
        val locale = getLocale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        return context.createConfigurationContext(config)
    }

    fun getLocale(languageCode: String): Locale = when (languageCode) {
        "ar" -> Locale("ar", "EG")  // matches values-ar-rEG
  //      "en" -> Locale("en", "US")  // matches values-en-rUS if you add one
        else -> Locale(languageCode)
    }
}