plugins {
    `java-library`
    kotlin("jvm")
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    testImplementation(project(":kris-io"))

    implementation(libs.bundles.kotlin)

    testImplementation(libs.bundles.testDeps)
}
