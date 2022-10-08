import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
}

tasks {
    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
}

dependencies {
    testImplementation(project(":kris-io"))

    implementation(libs.bundles.kotlin)

    testImplementation(libs.bundles.testDeps)
    testRuntimeOnly(libs.bundles.testEngines)
}
