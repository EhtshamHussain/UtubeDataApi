plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.utube_data_api"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.utube_data_api"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        buildConfig=true

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isTestCoverageEnabled = true

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
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Paging3 & Paging Compose
    implementation (libs.androidx.paging.runtime)
    implementation (libs.androidx.paging.compose)

    // Retrofit & Gson Converter
    implementation( libs.retrofit)
    implementation (libs.converter.gson)

    // Coil for image loading in Compose
    implementation (libs.coil.compose)

    // Lifecycle
    implementation (libs.androidx.lifecycle.runtime.ktx)

    // Retrofit (API calls ke liye)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

// Jetpack Compose ViewModel (UI aur logic ko alag rakhne ke liye)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}