plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "dev.stralman.flashcardio.shared"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }
}

dependencies {
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.room:room-common:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.5.4")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.google.accompanist:accompanist-themeadapter-material:0.28.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:1.10.1")
    implementation("androidx.fragment:fragment-ktx:1.5.2")
    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.androidx.room.compiler)
    kapt(libs.hilt.android.compiler)

    // Testing dependencies
    kaptAndroidTest(libs.hilt.android.compiler)
}