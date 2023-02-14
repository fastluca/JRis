plugins {
    id("kris-detekt")
    id("kris-collect-sarif")
    id("kris-publish")
    id("kris-jacoco")
    kotlin("jvm")
    `jvm-test-suite`
    alias(libs.plugins.dokka)
}

kotlin {
    explicitApi()
    jvmToolchain(libs.versions.java.get().toInt())
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        val integrationTest by registering(JvmTestSuite::class) {
            dependencies {
                implementation(project())
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

val integrationTestImplementation by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}

tasks {
    named("check") {
        dependsOn(testing.suites.named("integrationTest"))
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
}
