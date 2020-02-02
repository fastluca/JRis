val rxjavaVersion: String by project

dependencies {
    testImplementation(project(":jris-io"))
    testImplementation("io.reactivex.rxjava2:rxjava:$rxjavaVersion")
}
