import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kris-detekt")
    id("kris-collect-sarif")
    `java-library`
    kotlin("jvm")
    alias(libs.plugins.testSets)
}

kotlin {
    explicitApi()
}

testSets {
    create("integrationTest")
}

tasks {
    named("check") {
        dependsOn("integrationTest")
    }
}

dependencies {
    api(project(":kris-core"))

    implementation(libs.bundles.kotlin)

    testImplementation(libs.bundles.testDeps)
    testRuntimeOnly(libs.bundles.testEngines)
}
