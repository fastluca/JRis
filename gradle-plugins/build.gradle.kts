plugins {
    kotlin("jvm") version "1.8.10"
}

kotlin {
    jvmToolchain(11)
}

val buildTask = tasks.register("buildPlugins")

subprojects {
    buildTask.configure { dependsOn(tasks.named("build")) }
}
