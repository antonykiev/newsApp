package dependency

import implementation
import kapt
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.Hilt() {
    implementation(AppDependencies.Hilt.hiltAndroid)
    kapt(AppDependencies.Hilt.androidxHiltCompiler)
    kapt(AppDependencies.Hilt.hiltAndroidCompiler)
}
