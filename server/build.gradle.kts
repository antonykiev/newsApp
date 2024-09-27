import dependency.Hilt
import dependency.Project
import dependency.Retrofit
import dependency.Test

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndtoid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    namespace = "com.petproject.server"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    Hilt()
    Retrofit()
    Project(":core")
    Test()
}