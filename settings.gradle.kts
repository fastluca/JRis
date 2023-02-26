@file:Suppress("UnstableApiUsage")

import org.ajoberstar.reckon.gradle.ReckonExtension

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.ajoberstar.reckon.settings") version "0.17.0-beta.4"
}

configure<ReckonExtension> {
    stages("beta", "rc", "final", "bosch")
    setScopeCalc(calcScopeFromProp().or(calcScopeFromCommitMessages()))
    setStageCalc(calcStageFromProp())
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "KRis"

fun includeProject(projectDirName: String, projectName: String) {
    val baseDir = File(settingsDir, projectDirName)
    val projectDir = File(baseDir, projectName)
    val buildFileName = "${projectName}.gradle.kts"

    assert(projectDir.isDirectory)
    assert(File(projectDir, buildFileName).isFile)

    include(projectName)
    project(":$projectName").projectDir = projectDir
    project(":$projectName").buildFileName = buildFileName
}

listOf("docs", "subprojects").forEach { containerDir ->
    File(rootDir, containerDir)
        .walkTopDown().maxDepth(1)
        .forEach { projectDir ->
            val buildFile = File(projectDir, "${projectDir.name}.gradle.kts")
            if (buildFile.exists())
                includeProject(containerDir, projectDir.name)
        }
}

includeBuild("gradle-plugins")
