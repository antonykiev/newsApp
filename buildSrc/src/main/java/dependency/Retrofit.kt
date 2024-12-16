package dependency

import implementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.Retrofit() {
    implementation(AppDependencies.Retrofit.retrofit)
    implementation(AppDependencies.Retrofit.gsonConverter)
    implementation(AppDependencies.Retrofit.okHttp)
    implementation(AppDependencies.Retrofit.interceptor)
}

fun DependencyHandler.RetrofitTesting() {
    implementation(AppDependencies.Retrofit.retrofit)
    implementation(AppDependencies.Retrofit.gsonConverter)
    implementation(AppDependencies.Retrofit.okHttp)
    implementation(AppDependencies.Retrofit.interceptor)
}