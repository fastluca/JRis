@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
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

//buildscript {
//    repositories {
//        gradlePluginPortal()
//    }
//
//    val kotlinVersion: String by settings
//    val kordampPluginVersion: String by settings
//    val reckonVersion: String by settings
//    val gitPublishVersion: String by settings
//
//    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
//        classpath("org.ajoberstar.reckon:reckon-gradle:$reckonVersion")
//        classpath("org.ajoberstar:gradle-git-publish:$gitPublishVersion")
//    }
//}
