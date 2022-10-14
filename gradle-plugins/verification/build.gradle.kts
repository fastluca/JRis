import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    alias(libs.plugins.detekt)
}

dependencies {
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.detekt)
}

tasks.withType(KotlinJvmCompile::class.java).configureEach {
    kotlinOptions.jvmTarget = libs.versions.java.get()
}

detekt {
    buildUponDefaultConfig = true
    config.from(file("../../config/detekt/detekt.yml"))
}

gradlePlugin {
    plugins {
        create("kris-detekt") {
            id = "kris-detekt"
            implementationClass = "KrisDetektPlugin"
        }
        create("kris-collect-sarif") {
            id = "kris-collect-sarif"
            implementationClass = "CollectSarifPlugin"
        }
        create("kris-jacoco") {
            id = "kris-jacoco"
            implementationClass = "KrisJacocoPlugin"
        }
    }
}
