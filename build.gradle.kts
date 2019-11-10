import org.kordamp.gradle.plugin.integrationtest.IntegrationTestPlugin
import org.sonarqube.gradle.SonarQubeTask

plugins {
    kotlin("jvm")
    id("org.kordamp.gradle.project")
    id("org.kordamp.gradle.integration-test") apply false
    java
    id("org.sonarqube")
//    id("io.gitlab.arturbosch.detekt")
    id("org.ajoberstar.reckon")
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
        mavenLocal()
        jcenter()
        mavenCentral()
    }

    tasks {
        val deleteOutFolderTask by registering(Delete::class) {
            delete("out")
        }
        named("clean") {
            dependsOn(deleteOutFolderTask)
        }
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply<JavaPlugin>()
    apply<IdeaPlugin>()
    apply<IntegrationTestPlugin>()

    dependencies {
        implementation(Lib.kotlin("stdlib-jdk8"))
        implementation(Lib.kotlin("reflect"))
        implementation(Lib.kotlinx("coroutines-core"))

        implementation(Lib.junit5("api"))
        implementation(Lib.spek("dsl-jvm"))
        implementation(Lib.mockk())
        implementation(Lib.kluent())

        testRuntimeOnly(Lib.junit5("engine"))
        testRuntimeOnly(Lib.spek("runner-junit5"))
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

reckon {
    scopeFromProp()
    snapshotFromProp()
}

tasks {
    withType<SonarQubeTask> {
        description = "Push jacoco analysis to sonarcloud."
        group = "Verification"
        dependsOn(subprojects.map { it.tasks.getByName("jacocoTestReport") })
        // dependsOn(subprojects.filterNot { it.name.contains("java") }.map { it.tasks.getByName("detekt") })
    }
}
