val rxjavaVersion: String by project
val coroutinesVersion: String by project

dependencies {
    implementation("io.reactivex.rxjava2:rxjava:$rxjavaVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$coroutinesVersion")
}
