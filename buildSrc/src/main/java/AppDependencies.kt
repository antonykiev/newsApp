object AppDependencies {

    object Android {
        private const val coreKtx = "1.13.1"
        private const val appCompat = "1.7.0"
        private const val lifecycleRuntimeKtx = "2.8.4"

        const val androidxCooreKtx = "androidx.core:core-ktx:${coreKtx}"
        const val androidxAppcompat = "androidx.appcompat:appcompat:${appCompat}"
        const val androidxLifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycleRuntimeKtx}"
    }

    object Coroutines {
        private const val kotlinxCoroutines = "1.3.3"

        const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutines}"
        const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${kotlinxCoroutines}"
    }

    object Hilt {
        private const val hilt = "2.51.1"
        private const val hiltAndroidX = "1.2.0"
        private const val androidxHiltLifecycleViewmodelVersion = "1.0.0-alpha03"

        const val hiltAndroid = "com.google.dagger:hilt-android:${hilt}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${hilt}"
        const val androidxHiltCompiler = "androidx.hilt:hilt-compiler:${hiltAndroidX}"
        const val androidxHiltLifecycleViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${androidxHiltLifecycleViewmodelVersion}"
    }

    object Compose {
        private const val composeBomVersion = "2024.08.00"
        private const val activityComposeVersion = "1.9.1"
        private const val composeNavigationVersion = "2.8.0"

        const val composeMaterial3 = "androidx.compose.material3:material3"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
        const val composeCompiler = "androidx.compose.compiler:compiler"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val composeBom = "androidx.compose:compose-bom:${composeBomVersion}"
        const val composeActivity = "androidx.activity:activity-compose:${activityComposeVersion}"
        const val composeNavigation = "androidx.navigation:navigation-compose:${composeNavigationVersion}"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        private const val okHttpVersion = "4.9.1"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    object Test {
        const val androidxJunit = "androidx.test.ext:junit:${Versions.junitVersion}"
        const val junit = "junit:junit:${Versions.junit}"
        const val androidxUiTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val androidxUiTooling = "androidx.compose.ui:ui-tooling"
        const val androidxUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
        const val androidxEspressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
        const val material = "com.google.android.material:material:${Versions.material}"
    }

}