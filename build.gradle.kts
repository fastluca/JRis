plugins {
    kotlin("jvm")
    java
    id("org.kordamp.gradle.kotlin-project")
    id("org.kordamp.gradle.integration-test") apply false
    id("org.kordamp.gradle.detekt")
    id("org.kordamp.gradle.bintray")
//    id("org.kordamp.gradle.sonar") // TODO wait for org.kordamp.gradle.sonar (0.32.1 ?)
    id("org.ajoberstar.reckon")
    idea
}

config {
    release = rootProject.findProperty("release").toString().toBoolean()

    info {
        name = "KRis"
        vendor = "Private"
        description = "Library for reading/writing RIS files"
        inceptionYear = "2017"
        organization {
            url = "https://github.com/ursjoss/KRis.git"
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

    quality {
        detekt {
            buildUponDefaultConfig = true
            failFast = true
        }
// TODO wait for org.kordamp.gradle.sonar (0.32.1 ?)
//        sonar {
//            username = "ursjoss"
//        }
    }

    bintray {
        credentials {
            username = System.getenv("BINTRAY_USER")
            password = System.getenv("BINTRAY_KEY")
        }
        userOrg = "difty"
        githubRepo = "ursjoss/KRis"
        publish = true
        skipMavenSync = true
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
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
    if (project.name != "guide") {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply<JavaPlugin>()
        apply<IdeaPlugin>()
        apply<JacocoPlugin>()

        val assertjVersion: String by project
        val coroutinesVersion: String by project
        val junitJupiterVersion: String by project
        val kluentVersion: String by project
        val kotlinVersion: String by project
        val mockkVersion: String by project
        val spekVersion: String by project

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
    }
}

reckon {
    scopeFromProp()
    stageFromProp("beta", "rc", "final")
}
