@file:Suppress("UnstableApiUsage")

import org.kordamp.gradle.plugin.settings.ProjectsExtension
import org.kordamp.gradle.plugin.settings.SettingsPlugin

pluginManagement {
    repositories {
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version "1.3.61"
        val kordampPluginVersion = "0.30.4"
        id("org.kordamp.gradle.project") version kordampPluginVersion
        id("org.kordamp.gradle.integration-test") version kordampPluginVersion
        id("org.kordamp.gradle.guide") version kordampPluginVersion
        id("org.sonarqube") version "2.8"
        id("io.gitlab.arturbosch.detekt") version "1.2.2"
        id("org.ajoberstar.reckon") version "0.12.0"
        id("org.ajoberstar.git-publish") version "2.1.3-rc.1"
    }
}
buildscript {
    repositories {
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }
    val kordampPluginVersion = "0.30.4"
    dependencies {
        classpath("org.kordamp.gradle:settings-gradle-plugin:$kordampPluginVersion")
    }
}

apply<SettingsPlugin>()

rootProject.name = "JRis"

configure<ProjectsExtension> {
    layout = "two-level"
    directories = listOf("docs", "subprojects")
}
