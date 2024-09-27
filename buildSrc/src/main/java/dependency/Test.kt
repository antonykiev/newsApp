package dependency

import AppDependencies
import testImplementation
import debugImplementation
import androidTestImplementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.Test() {
    testImplementation(AppDependencies.Test.junit)
//    debugImplementation(AppDependencies.Test.androidxUiTestManifest)
//    debugImplementation(AppDependencies.Test.androidxUiTooling)
//    androidTestImplementation(AppDependencies.Test.androidxUiTestJunit4)
    androidTestImplementation(AppDependencies.Test.androidxJunit)
    androidTestImplementation(AppDependencies.Test.androidxEspressoCore)
//    androidTestImplementation(platform(AppDependencies.Compose.composeBom))
}