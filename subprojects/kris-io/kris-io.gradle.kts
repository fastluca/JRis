@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("kris-detekt")
    id("kris-collect-sarif")
    id("kris-publish")
    id("kris-jacoco")
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
    val javadocJar by existing(Jar::class) {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        description = "Assembled Javadoc JAR"
        archiveClassifier.set("javadoc")
        from(named("dokkaHtml"))
    }
    named("sourcesJar") {
        dependsOn(javadocJar)
    }
}

dependencies {
    api(project(":kris-core"))

    implementation(libs.bundles.kotlin)

    testImplementation(libs.bundles.testDeps)
    testRuntimeOnly(libs.bundles.testEngines)
}
