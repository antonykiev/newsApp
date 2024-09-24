package dependency

import implementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.Android() {
    implementation(AppDependencies.Android.androidxCooreKtx)
    implementation(AppDependencies.Android.androidxAppcompat)
    implementation(AppDependencies.Android.androidxLifecycleRuntimeKtx)
}