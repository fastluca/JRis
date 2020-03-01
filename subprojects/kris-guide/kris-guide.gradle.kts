val rxjavaVersion: String by project

dependencies {
    testImplementation(project(":kris-io"))
    testImplementation("io.reactivex.rxjava2:rxjava:$rxjavaVersion")
}
