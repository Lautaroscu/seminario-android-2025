import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.parcelizeKotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
}
val localProperties = gradleLocalProperties(rootDir,
    providers
)
val baseUrl: String = "https://api.rawg.io/api/"
val apiKey: String = "e0358f4b4c7f42e8a25b9601312d06b5"

android {
    namespace = "ar.edu.unicen.seminario2025"
    compileSdk = 36

    defaultConfig {
        applicationId = "ar.edu.unicen.seminario2025"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    flavorDimensions += listOf("environment")

    productFlavors {
        create("develop") {
            applicationIdSuffix = ".develop"
            versionNameSuffix = "-develop"
            dimension = "environment"
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activityKtx)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.glide)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.materail3)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.glide.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)


}