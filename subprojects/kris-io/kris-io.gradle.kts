import org.jetbrains.dokka.plugability.configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("kris-detekt")
    id("kris-collect-sarif")
    id("kris-publish")
    id("kris-jacoco")
    kotlin("jvm")
    alias(libs.plugins.dokka)
    `jvm-test-suite`
}

kotlin {
    explicitApi()
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
        register<JvmTestSuite>("integrationTest") {
            testType.set(TestSuiteType.INTEGRATION_TEST)
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
        configurations.named("integrationTestImplementation") {
            extendsFrom(configurations.named("testImplementation").get())
        }
    }
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
