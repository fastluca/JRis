plugins {
    id("org.kordamp.gradle.integration-test")
}

dependencies {
    implementation(Lib.rxjava2())
    implementation(Lib.rxkotlin())
    implementation(Lib.kotlinx("coroutines-rx2"))

    testImplementation(Lib.assertJ())
}
