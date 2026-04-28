import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
 //  alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}
val keyProperties = Properties()
val keyFile = rootProject.file("key.properties")

if (keyFile.exists()) {
    keyProperties.load(FileInputStream(keyFile))
} else {

    throw GradleException("key.properties file not found")
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
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"

            buildConfigField(
                "String",
                "BASE_URL",
                "\"${keyProperties["DEV_BASE_URL"]}\""
            )

            buildConfigField(
                "String",
                "ENVIRONMENT",
                "\"${keyProperties["DEV_ENV"]}\""
            )

            resValue("string", "app_name", "CareLine Dev")
        }

        create("staging") {
            dimension = "environment"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"

            buildConfigField(
                "String",
                "BASE_URL",
                "\"${keyProperties["STAGING_BASE_URL"]}\""
            )

            buildConfigField(
                "String",
                "ENVIRONMENT",
                "\"${keyProperties["STAGING_ENV"]}\""
            )

            resValue("string", "app_name", "CareLine Staging")
        }

        create("production") {
            dimension = "environment"

            buildConfigField(
                "String",
                "BASE_URL",
                "\"${keyProperties["PROD_BASE_URL"]}\""
            )

            buildConfigField(
                "String",
                "ENVIRONMENT",
                "\"${keyProperties["PROD_ENV"]}\""
            )

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
    implementation("androidx.compose.material:material-icons-extended")
    implementation(libs.lottie.compose)
    // Core
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.material3.xml)
    // Compose (BOM manages all versions — never specify compose versions individually)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
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