plugins {
    id("kris-detekt")
    id("kris-collect-sarif")
    id("kris-publish")
    id("kris-jacoco")
    kotlin("jvm")
    alias(libs.plugins.dokka)
}

dependencies {
    api(libs.rxjava.get())

    implementation(libs.bundles.kotlin)
    implementation(libs.coroutines.rx2)

    testImplementation(libs.bundles.testDeps)
    testRuntimeOnly(libs.bundles.testEngines)
}

kotlin {
    explicitApi()
}

tasks {
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
