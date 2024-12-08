package dependency

import implementation
import debugImplementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.Compose() {
    implementation(AppDependencies.Compose.composeActivity)
    implementation(platform(AppDependencies.Compose.composeBom))
    implementation(AppDependencies.Compose.composeUi)
    //implementation(AppDependencies.Compose.composeCompiler)
    implementation(AppDependencies.Compose.composeUiGraphics)
    implementation(AppDependencies.Compose.composeUiToolingPreview)
    implementation(AppDependencies.Compose.composeMaterial3)
    implementation(AppDependencies.Compose.composeNavigation)
    implementation(AppDependencies.Compose.composeAnimation)
    debugImplementation(AppDependencies.Compose.composeUiTooling)
}
