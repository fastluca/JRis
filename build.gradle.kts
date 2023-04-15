import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.plugin.kotlin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    id("kris-collect-sarif")
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.dokka)
    alias(libs.plugins.nexusPublish)
    alias(libs.plugins.binaryCompatValidator)
    `maven-publish`
    jacoco
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
}

val jacocoTestReportFile = "${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml"

jacoco {
    toolVersion = libs.versions.jacoco.get()
}


sonarqube {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectKey", "ursjoss_${project.name}")
        property("sonar.organization", "ursjoss-github")
        property("sonar.coverage.jacoco.xmlReportPaths", jacocoTestReportFile)
        property("sonar.kotlin.detekt.reportPaths", "${project.buildDir}/reports/detekt/detekt.xml")
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            val ossrhUsername = providers.environmentVariable("OSSRH_USERNAME")
            val ossrhPassword = providers.environmentVariable("OSSRH_PASSWORD")
            if (ossrhUsername.isPresent && ossrhPassword.isPresent) {
                username.set(ossrhUsername.get())
                password.set(ossrhPassword.get())
            }
        }
    }
}

tasks {
    val deleteOutFolderTask by registering(Delete::class) {
        delete("out")
    }
    named("clean") {
        delete(rootProject.buildDir)
        dependsOn(deleteOutFolderTask)
    }
    val dokkaHtml by getting(DokkaTask::class) {
        dokkaSourceSets {
            configureEach {
                sourceLink {
                    localDirectory.set(file("$projectDir/$kotlinSrcSet"))
                    remoteUrl.set(URL(projectRelativeSourceLink()))
                    remoteLineSuffix.set("#L")
                }
            }
        }
    }
}

val kotlinSrcSet = "/src/main/kotlin"

subprojects.forEach { subProject ->
    subProject.tasks {
        val kotlinVersion = libs.versions.kotlin.get()
        val kotlinApiLangVersion = kotlinVersion.subSequence(0, 3).toString()
        val jvmTargetVersion = libs.versions.java.get()
        withType<KotlinCompile>().configureEach {
            kotlinOptions {
                apiVersion = kotlinApiLangVersion
                languageVersion = kotlinApiLangVersion
                jvmTarget = jvmTargetVersion
                freeCompilerArgs = freeCompilerArgs + listOf("-opt-in=kotlin.RequiresOptIn")
            }
        }
        withType<JavaCompile>().configureEach {
            sourceCompatibility = jvmTargetVersion
            targetCompatibility = jvmTargetVersion
        }

        withType<Test> {
            useJUnitPlatform {
                includeEngines("junit-jupiter", "kotest")
            }
        }
        withType<DokkaTaskPartial>().configureEach {
            dokkaSourceSets {
                configureEach {
                    includes.from("module.md")
                }
            }
        }
    }
}


fun Project.projectRelativeSourceLink(branch: String = "main", srcSet: String = kotlinSrcSet) =
    "https://github.com/ursjoss/KRis/blob/$branch/${projectDir.relativeTo(rootDir)}/$srcSet"

fun String.mayHaveTestCoverage(): Boolean = startsWith("kris")
