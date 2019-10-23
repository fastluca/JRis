import org.kordamp.gradle.plugin.settings.ProjectsExtension

buildscript {
    repositories {
        jcenter()
        gradlePluginPortal()
        mavenLocal()
    }
    dependencies {
        classpath("org.kordamp.gradle:settings-gradle-plugin:0.27.0")
    }
}

apply(plugin = "org.kordamp.gradle.settings")

rootProject.name = "JRis"

configure<ProjectsExtension> {
    layout = "two-level"
    directories = listOf("docs", "subprojects")
}
