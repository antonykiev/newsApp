package dependency

import implementation
import kapt
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.Room() {
    implementation(AppDependencies.Room.roomRuntime)
    implementation(AppDependencies.Room.ktx)
    kapt(AppDependencies.Room.compiler)
}