plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    alias(libs.plugins.detekt)
}

dependencies {
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.detekt)
}

detekt {
    buildUponDefaultConfig = true
    config.from(file("../../config/detekt/detekt.yml"))
}

gradlePlugin {
    plugins {
        create("kris-publish") {
            id = "kris-publish"
            implementationClass = "KrisPublishPlugin"
        }
    }
}
