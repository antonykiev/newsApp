package dependency

import AppDependencies
import implementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.Coroutines() {
    implementation(AppDependencies.Coroutines.kotlinxCoroutinesAndroid)
    implementation(AppDependencies.Coroutines.kotlinxCoroutinesCore)
}
