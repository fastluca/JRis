import org.sonarqube.gradle.SonarQubeTask

plugins {
    kotlin("jvm") version "1.3.50"
    id("org.kordamp.gradle.project") version "0.27.0"
    java
    id("org.sonarqube") version "2.8"
//    id("io.gitlab.arturbosch.detekt") version "1.1.1"
}

config {
    release = rootProject.findProperty("release").toString().toBoolean()

    info {
        name = "JRis"
        vendor = "Private"
        description = "Library for reading/writing RIS files"
        inceptionYear = "2017"
        organization {
            url = "https://github.com/ursjoss/JRis.git"
        }
        links {
            scm = "https://github.com/ursjoss/JRis.git"
        }
        people {
            person {
                id = "ursjoss"
                name = "Urs Joss"
                roles = listOf("developer")
            }
            person {
                id = "fastluca"
                name = "Gianluca Colaianni"
                roles = listOf("developer")
            }
        }
    }

    licensing {
        enabled = false
        licenses {
            license {
                id = "MIT"
            }
        }
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply<JavaPlugin>()
    apply<IdeaPlugin>()

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
        testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.8")
        testImplementation("io.mockk:mockk:1.9.3")
        testImplementation("org.amshove.kluent:kluent:1.55")

        testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.8")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    }

    tasks {
        withType<Test> {
            useJUnitPlatform {
                includeEngines("junit-jupiter", "spek2")
            }
        }
    }

    /*
    detekt {
        failFast = false
        buildUponDefaultConfig = true
        config = files("$rootDir/detekt-config.yml")
        baseline = file("detekt-baseline.xml")

        reports {
            xml.enabled = true
            html.enabled = true
        }
    }
    */
}

val jacocoTestReportFile = "$buildDir/reports/jacoco/test/jacocoTestReport.xml"

sonarqube {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectKey", "ursjoss_JRis")
        property("sonar.organization", "ursjoss-github")
        property("sonar.coverage.jacoco.xmlReportPaths", jacocoTestReportFile)
        // property("sonar.kotlin.detekt.reportPaths", "build/reports/detekt/detekt.xml")
    }
}

tasks {
    withType<SonarQubeTask> {
        description = "Push jacoco analysis to sonarcloud."
        group = "Verification"
        dependsOn(subprojects.map { it.tasks.getByName("jacocoTestReport") })
        // dependsOn(subprojects.filterNot { it.name.contains("java") }.map { it.tasks.getByName("detekt") })
    }
}
