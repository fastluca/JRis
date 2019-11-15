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
        kotlin("jvm") version "1.3.50"
        val kordampPluginVersion = "0.30.0-SNAPSHOT"
        id("org.kordamp.gradle.project") version kordampPluginVersion
        id("org.kordamp.gradle.integration-test") version kordampPluginVersion
        id("org.kordamp.gradle.guide") version kordampPluginVersion
        id("org.sonarqube") version "2.8"
        id("io.gitlab.arturbosch.detekt") version "1.1.1"
        id("org.ajoberstar.reckon") version "0.11.0"
    }
}
buildscript {
    repositories {
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }
    val kordampPluginVersion = "0.30.0-SNAPSHOT"
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
