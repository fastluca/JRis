import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.plugin.kotlin)
        classpath(libs.plugin.reckon)
    }
}

plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    id("kris-collect-sarif")
    alias(libs.plugins.reckon)
    alias(libs.plugins.sonarqube)
}

reckon {
    setScopeCalc(calcScopeFromProp().or(calcScopeFromCommitMessages()))
    setStageCalc(calcStageFromProp())
}
sonarqube {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectKey", "ursjoss_${project.name}")
        property("sonar.organization", "ursjoss-github")
        property("sonar.kotlin.detekt.reportPaths", "build/reports/detekt/detekt.xml")
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
}

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
                includeEngines("junit-jupiter", "spek2")
            }
        }
    }
}

/*
val kotlinVersion: String by project
val javaVersion = JavaVersion.VERSION_11
val kotlinSrcSet = "/src/main/kotlin"
val srcLinkSuffix = "#L"
val sonarToken = System.getenv("SONAR_TOKEN") ?: "n.a."

config {
    release = rootProject.findProperty("release").toString().toBoolean()

    info {
        name = "KRis"
        vendor = "Private"
        description = "Library for reading/writing RIS files"
        inceptionYear = "2017"
        organization {
            url = "https://github.com/ursjoss/KRis"
        }
        links {
            website = "https://ursjoss.github.io/KRis"
            scm = "https://github.com/ursjoss/KRis.git"
            issueTracker = "https://github.com/ursjoss/KRis/issues"
        }
        ciManagement {
            url = "https://github.com/ursjoss/KRis/actions"
        }
        issueManagement {
            url = "https://github.com/ursjoss/KRis/issues"
        }
        people {
            person {
                id = "ursjoss"
                name = "Urs Joss"
                roles = listOf("developer")
                properties["github"] = "ursjoss"
                properties["twitter"] = "urs_j_o_s_s"
            }
            person {
                id = "fastluca"
                name = "Gianluca Colaianni"
                roles = listOf("developer")
                properties["github"] = "fastluca"
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

    coverage {
        jacoco {
            includeProjectDependencies = true
        }
    }

    quality {
        detekt {
            buildUponDefaultConfig = true
            failFast = false
        }

        sonar {
            hostUrl = "https://sonarcloud.io"
            login = sonarToken
            organization = "ursjoss-github"
            projectKey = "ursjoss_${project.name}"
        }
    }

    bintray {
        enabled = true
        credentials {
            username = System.getenv("BINTRAY_USER") ?: "**UNDEFINED**"
            password = System.getenv("BINTRAY_KEY") ?: "**UNDEFINED**"
        }
        userOrg = "difty"
        githubRepo = "ursjoss/KRis"
        publish = true
        skipMavenSync = true
    }

    docs {
        javadoc {
            enabled = false
        }

        kotlindoc {
            enabled = true
            replaceJavadoc = true
            includes = project.subprojects.filterNot { it.name == "guide" }.map { sp ->
                "${sp.projectDir}/module.md"
            }.toList()
            jdkVersion = javaVersion.majorVersion.toInt()

            aggregate {
                enabled = true
                fast = false
                replaceJavadoc = true
            }
            project.subprojects.forEach { subProject ->
                sourceLinks {
                    sourceLink {
                        localDirectory = "${subProject.projectDir}/$kotlinSrcSet"
                        remoteUrl = subProject.projectRelativSourceLink()
                        remoteLineSuffix = srcLinkSuffix
                    }
                }
            }
        }
    }
}


configure<ProjectsExtension> {
    all {
        path("*") {
            apply<IdeaPlugin>()
            apply(plugin = "org.jetbrains.kotlin.jvm")

            repositories {
                mavenLocal()
                mavenCentral()
                jcenter()
            }

            kotlin {
                explicitApi()
            }

            tasks {
                val deleteOutFolderTask by registering(Delete::class) {
                    delete("out")
                }
                named("clean").configure {
                    dependsOn(deleteOutFolderTask)
                }
                val kotlinApiLangVersion = kotlinVersion.subSequence(0, 3).toString()
                val jvmTargetVersion = javaVersion.toString()
                withType<KotlinCompile>().configureEach {
                    kotlinOptions {
                        apiVersion = kotlinApiLangVersion
                        languageVersion = kotlinApiLangVersion
                        jvmTarget = jvmTargetVersion
                        freeCompilerArgs = freeCompilerArgs + listOf("-Xopt-in=kotlin.RequiresOptIn")
                    }
                }
                withType<JavaCompile>().configureEach {
                    sourceCompatibility = jvmTargetVersion
                    targetCompatibility = jvmTargetVersion
                }
                withType<Detekt>().configureEach {
                    jvmTarget = jvmTargetVersion
                }
            }
        }

        path(":") {
            tasks {
                val aggregateDetekt by existing {
                    dependsOn(subprojects.map { it.tasks.getByName("detekt") })
                }
                named("sonarqube").configure {
                    dependsOn(aggregateDetekt)
                }
            }
        }

        dir("subprojects") {
            config {
                docs {
                    kotlindoc {
                        enabled = false
                        sourceLinks {
                            sourceLink {
                                localDirectory = "$projectDir/$kotlinSrcSet"
                                remoteUrl = project.projectRelativSourceLink()
                                remoteLineSuffix = srcLinkSuffix
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Project.projectRelativSourceLink(branch: String = "main", srcSet: String = kotlinSrcSet) =
    rootProject.config.info.links.scm?.let { scmUrl ->
        "${scmUrl.substringBefore(".git")}/blob/$branch/${projectDir.relativeTo(rootDir)}/$srcSet"
    }
*/
