package dependency

import androidTestImplementation
import implementation
import kapt
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.Hilt() {
    implementation(AppDependencies.Hilt.hiltAndroid)
    kapt(AppDependencies.Hilt.androidxHiltCompiler)
    kapt(AppDependencies.Hilt.hiltAndroidCompiler)
}

fun DependencyHandler.HiltTesting() {
    androidTestImplementation(AppDependencies.Hilt.hiltTesting)
    kapt(AppDependencies.Hilt.androidxHiltCompiler)
    kapt(AppDependencies.Hilt.hiltAndroidCompiler)
}
