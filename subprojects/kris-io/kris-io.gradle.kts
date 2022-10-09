plugins {
    id("kris-detekt")
    id("kris-collect-sarif")
    `java-library`
    kotlin("jvm")
    alias(libs.plugins.testSets)
    alias(libs.plugins.dokka)
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
