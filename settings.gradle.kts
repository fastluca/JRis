@file:Suppress("UnstableApiUsage")

import org.kordamp.gradle.plugin.settings.ProjectsExtension
import org.kordamp.gradle.plugin.settings.SettingsPlugin

pluginManagement {
    repositories {
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }

    val kotlinVersion: String by settings
    val kordampPluginVersion: String by settings
    val reckonVersion: String by settings
    val gitPublishVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        id("org.kordamp.gradle.kotlin-project") version kordampPluginVersion
        id("org.kordamp.gradle.integration-test") version kordampPluginVersion
        id("org.kordamp.gradle.guide") version kordampPluginVersion
        id("org.kordamp.gradle.detekt") version kordampPluginVersion
        id("org.kordamp.gradle.sonar") version kordampPluginVersion
        id("org.ajoberstar.reckon") version reckonVersion
        id("org.ajoberstar.git-publish") version gitPublishVersion
    }
}
buildscript {
    repositories {
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }
    val kordampPluginVersion: String by settings
    dependencies {
        classpath("org.kordamp.gradle:settings-gradle-plugin:$kordampPluginVersion")
    }
}

apply<SettingsPlugin>()

rootProject.name = "KRis"

configure<ProjectsExtension> {
    layout = "two-level"
    directories = listOf("docs", "subprojects")
}
