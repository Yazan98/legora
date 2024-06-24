plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.yazantarifi.legora.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.yazantarifi.legora.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        renderscriptSupportModeEnabled = true
        renderscriptTargetApi = 26
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
        kotlinOptions.freeCompilerArgs += "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=androidx.compose.ui.ExperimentalComposeUiApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    implementation(libs.gson)
    implementation(libs.compose)
    implementation(libs.androidx.security.crypto.ktx)

    implementation(libs.timber)
    implementation(libs.androidx.multidex)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.lottie.compose)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android.v164)

    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.androidx.hilt.compiler)

    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.serialization.jvm)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.room.runtime)
    implementation(libs.sqlite.bundled)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
