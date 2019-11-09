import org.kordamp.gradle.plugin.settings.ProjectsExtension

pluginManagement {
    repositories {
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }
}
buildscript {
    repositories {
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }
    dependencies {
        classpath("org.kordamp.gradle:settings-gradle-plugin:0.30.0-SNAPSHOT")
    }
}

apply(plugin = "org.kordamp.gradle.settings")

rootProject.name = "JRis"

configure<ProjectsExtension> {
    layout = "two-level"
    directories = listOf("docs", "subprojects")
}
