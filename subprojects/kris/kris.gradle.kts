import org.kordamp.gradle.plugin.integrationtest.IntegrationTestPlugin

apply<IntegrationTestPlugin>()

dependencies {
    implementation("io.reactivex.rxjava2:rxjava:2.2.13")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.3.2")
}
