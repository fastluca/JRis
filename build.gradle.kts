plugins {
    kotlin("jvm") version "1.3.50"
    id("org.kordamp.gradle.project") version "0.27.0"
    java
}

config {
    release = rootProject.findProperty("release").toString().toBoolean()

    info {
        name = "JRis"
        vendor = "Private"
        description = "Importing/exporting bibliography data in RIS format"
        inceptionYear = "2017"
        organization {
            url = "https://github.com/ursjoss/JRis.git"
        }
        links {
            scm = "https://github.com/ursjoss/JRis.git"
        }
        people {
            person {
                id = "fastluca"
                name = "Gianluca Colaianni"
                roles = listOf("developer")
            }

            person {
                id = "ursjoss"
                name = "Urs Joss"
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
}