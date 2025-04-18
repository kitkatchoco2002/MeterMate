plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.khoco.metermate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.khoco.metermate"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Custom added dependencies
    // Google Fonts for Compose
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")
    // Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")
    // Material 3
    implementation("androidx.compose.material3:material3:1.1.2")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Optional: Coroutine support for Retrofit
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

}