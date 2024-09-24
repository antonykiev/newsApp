package dependency

import implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.Project(projectName: String) {
    implementation(project(projectName))
}
