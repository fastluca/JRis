@file:Suppress("SpellCheckingInspection")

import org.kordamp.gradle.plugin.base.ProjectsExtension

plugins {
    id("org.ajoberstar.reckon")
}

val kotlinSrcSet = "/src/main/kotlin"
val srcLinkSuffix = "#L"

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
            username = "ursjoss"
            configProperties["sonar.organization"] = "ursjoss-github"
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
            jdkVersion = 8

            aggregate {
                enabled = true
                fast = false
                replaceJavadoc = true
            }
            project.subprojects.forEach { subProject ->
                sourceLinks {
                    sourceLink {
                        path = "${subProject.projectDir}/$kotlinSrcSet"
                        url = subProject.projectRelativSourceLink()
                        suffix = srcLinkSuffix
                    }
                }
            }
        }
    }
}

reckon {
    scopeFromProp()
    stageFromProp("beta", "rc", "final")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

configure<ProjectsExtension> {
    all {
        path("*") {
            apply<IdeaPlugin>()

            repositories {
                mavenLocal()
                jcenter()
                mavenCentral()
            }

            tasks {
                val deleteOutFolderTask by registering(Delete::class) {
                    delete("out")
                }
                named("clean").configure {
                    dependsOn(deleteOutFolderTask)
                }
                withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
                    kotlinOptions.apiVersion = "1.3"
                    kotlinOptions.languageVersion = "1.3"
                    kotlinOptions.jvmTarget = "1.8"
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
            val assertjVersion: String by project
            val coroutinesVersion: String by project
            val kluentVersion: String by project
            val kotlinVersion: String by project
            val junitJupiterVersion: String by project
            val spekVersion: String by project
            val mockkVersion: String by project

            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
                implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
                testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
                testImplementation("io.mockk:mockk:$mockkVersion")
                testImplementation("org.amshove.kluent:kluent:$kluentVersion")
                testImplementation("org.assertj:assertj-core:$assertjVersion")

                testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
                testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
            }

            tasks {
                withType<Test> {
                    @Suppress("UnstableApiUsage")
                    useJUnitPlatform {
                        includeEngines("junit-jupiter", "spek2")
                    }
                }
            }

            config {
                docs {
                    kotlindoc {
                        sourceLinks {
                            sourceLink {
                                path = "$projectDir/$kotlinSrcSet"
                                url = project.projectRelativSourceLink()
                                suffix = srcLinkSuffix
                            }
                        }
                    }
                }
            }
        }

        val rxjavaVersion: String by project

        path(":kris-core") {
            val coroutinesVersion: String by project
            dependencies {
                implementation("io.reactivex.rxjava2:rxjava:$rxjavaVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$coroutinesVersion")
            }
        }

        path(":kris-guide") {
            dependencies {
                testImplementation("io.reactivex.rxjava2:rxjava:$rxjavaVersion")
            }
        }
    }
}

fun Project.projectRelativSourceLink(branch: String = "main", srcSet: String = kotlinSrcSet) =
    rootProject.config.info.links.scm?.let { scmUrl ->
        "${scmUrl.substringBefore(".git")}/blob/$branch/${projectDir.relativeTo(rootDir)}/$srcSet"
    }
