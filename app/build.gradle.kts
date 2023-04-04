plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.bootcounter"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
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
    implementation(Libs.constraintLayout)

    implementation(Libs.coreKtx)
    implementation(Libs.appCompat)
    implementation(Libs.material)
    implementation(Libs.activity)
    implementation(Libs.activityKtx)

    implementation(Libs.kotlinxCoroutinesAndroid)
    implementation(Libs.workManager)

    implementation(Libs.room)
    implementation(Libs.roomRuntime)
    kapt(Libs.roomCompiler)
}