import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import org.kordamp.gradle.plugin.integrationtest.IntegrationTestPlugin
import org.sonarqube.gradle.SonarQubeTask

plugins {
    kotlin("jvm")
    id("org.kordamp.gradle.project")
    java
    id("org.kordamp.gradle.integration-test") apply false
    id("org.sonarqube")
    id("io.gitlab.arturbosch.detekt")
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
    apply<JacocoPlugin>()
    apply<DetektPlugin>()

    dependencies {
        implementation(Lib.kotlin("stdlib-jdk8"))
        implementation(Lib.kotlin("reflect"))
        implementation(Lib.kotlinx("coroutines-core"))

        testImplementation(Lib.junit5("api"))
        testImplementation(Lib.spek("dsl-jvm"))
        testImplementation(Lib.mockk())
        testImplementation(Lib.kluent())

        testRuntimeOnly(Lib.junit5("engine"))
        testRuntimeOnly(Lib.spek("runner-junit5"))
    }

    tasks {
        withType<Test> {
            @Suppress("UnstableApiUsage")
            useJUnitPlatform {
                includeEngines("junit-jupiter", "spek2")
            }
        }
    }

}

reckon {
    scopeFromProp()
    stageFromProp("beta", "rc", "final")
}

tasks {
    withType<Detekt> {
        buildUponDefaultConfig = true
        failFast = false
        file("${rootProject.projectDir}/detekt-config.yml").takeIf { it.exists() }?.let {
            config.setFrom(it)
        }
        file("${rootProject.projectDir}/detekt-baseline.xml").takeIf { it.exists() }?.let {
            baseline.set(it)
        }
        source = fileTree(rootProject.projectDir)
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/")
        exclude("**/build/")
        reports {
            html {
                enabled = true
                destination = file("${rootProject.buildDir}/reports/detekt.html")
            }
            xml {
                enabled = true
                destination = file("${rootProject.buildDir}/reports/detekt.xml")
            }
        }
    }
    withType<SonarQubeTask> {
        description = "Push jacoco analysis to sonarcloud."
        group = "Verification"
        subprojects.filter { it.name == "kris" }.forEach {
            dependsOn("${it.path}:integrationTest")
            dependsOn("${it.path}:jacocoTestReport")
        }
        dependsOn("detekt")
        dependsOn("jacocoRootReport")
    }
}


sonarqube {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectKey", "ursjoss_JRis")
        property("sonar.organization", "ursjoss-github")
        property("sonar.coverage.jacoco.xmlReportPaths", "${rootProject.buildDir}/reports/jacoco/root/jacocoTestReport.xml")
        property("sonar.kotlin.detekt.reportPaths", "$buildDir/reports/detekt.xml")
    }
}
