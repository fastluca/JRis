@file:Suppress("UnstableApiUsage")

import org.kordamp.gradle.plugin.settings.ProjectsExtension
import org.kordamp.gradle.plugin.settings.SettingsPlugin

buildscript {
    repositories {
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }

    val kotlinVersion: String by settings
    val kordampPluginVersion: String by settings
    val reckonVersion: String by settings
    val gitPublishVersion: String by settings

    dependencies {
        classpath("org.kordamp.gradle:settings-gradle-plugin:$kordampPluginVersion")

        classpath("org.kordamp.gradle:kotlin-project-gradle-plugin:$kordampPluginVersion")
        classpath("org.kordamp.gradle:guide-gradle-plugin:$kordampPluginVersion")
        classpath("org.kordamp.gradle:kotlindoc-gradle-plugin:$kordampPluginVersion")
        classpath("org.kordamp.gradle:integrationtest-gradle-plugin:$kordampPluginVersion")
        classpath("org.kordamp.gradle:detekt-gradle-plugin:$kordampPluginVersion")
        classpath("org.kordamp.gradle:sonar-gradle-plugin:$kordampPluginVersion")
        classpath("org.kordamp.gradle:bintray-gradle-plugin:$kordampPluginVersion")

        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.ajoberstar.reckon:reckon-gradle:$reckonVersion")
        classpath("org.ajoberstar:gradle-git-publish:$gitPublishVersion")
    }
}

rootProject.name = "KRis"

apply<SettingsPlugin>()

configure<ProjectsExtension> {
    layout.set("two-level")
    directories.addAll(listOf("docs", "subprojects"))

    plugins {
        path(":") {
            id("java")
            id("org.kordamp.gradle.kotlin-project")
            id("org.kordamp.gradle.sonar")
            id("org.kordamp.gradle.detekt")
            id("org.kordamp.gradle.bintray")
        }
        path(":guide") {
            id("org.kordamp.gradle.guide")
            id("org.ajoberstar.git-publish")
        }
        dir("subprojects") {
            id("org.jetbrains.kotlin.jvm")
            id("org.kordamp.gradle.kotlindoc")
        }
        path(":kris-io") {
            id("org.kordamp.gradle.integration-test")
        }
    }
}
