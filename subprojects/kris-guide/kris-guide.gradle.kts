val rxjavaVersion: String by project

dependencies {
    testImplementation(project(":kris-io"))
    testImplementation("io.reactivex.rxjava2:rxjava:$rxjavaVersion")
}

tasks {
    withType<com.jfrog.bintray.gradle.tasks.BintrayUploadTask> {
        enabled = false
    }
}