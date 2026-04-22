plugins {
    alias(libs.plugins.android.application)
 //  alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.careline.clinicapp"
    compileSdk {
        version = release(36)
      //  compileSdk = 36
    }

    defaultConfig {
        applicationId = "com.careline.clinicapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        resourceConfigurations += listOf("ar", "en")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // ─── Build Flavors ──────────────────────────────────────────────────────
    // Think of these like Flutter's --dart-define environments.
    // Each flavor produces a separate APK/AAB with its own config.
    flavorDimensions += "environment"
    productFlavors {
        create("dev"){
            dimension = "environment"
            applicationIdSuffix = ".dev"          // com.careline.clinicapp.dev
            versionNameSuffix = "-dev"
            buildConfigField("String", "BASE_URL", "\"https://dev.your-api.com/api/v1/\"")
            buildConfigField("String", "ENVIRONMENT", "\"dev\"")
            resValue("string", "app_name", "CareLine Dev")
        }
        create("staging") {
            dimension = "environment"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            buildConfigField("String", "BASE_URL", "\"https://staging.your-api.com/api/v1/\"")
            buildConfigField("String", "ENVIRONMENT", "\"staging\"")
            resValue("string", "app_name", "CareLine Staging")
        }
        create("production") {
            dimension = "environment"
            // No suffix for production — clean package name
            buildConfigField("String", "BASE_URL", "\"https://api.your-api.com/api/v1/\"")
            buildConfigField("String", "ENVIRONMENT", "\"production\"")
            resValue("string", "app_name", "CareLine")
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose (BOM manages all versions — never specify compose versions individually)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit.junit)
    // implementation(libs.androidx.compose.material3.i)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization)

    // Storage
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    //ksp(libs.androidx.room.compiler)

    // Image Loading
    implementation(libs.coil.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(kotlin("test"))
}