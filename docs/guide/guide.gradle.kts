import org.ajoberstar.gradle.git.publish.tasks.GitPublishCommit
import org.asciidoctor.gradle.jvm.AsciidoctorTask
import java.time.Year

plugins {
    alias(libs.plugins.asciidoctorConvert)
    alias(libs.plugins.gitPublish)
}

tasks {
    val builtGuideDir = file("${project.buildDir}/guide")
    withType<AsciidoctorTask> {
        sourceDir(file("src/docs/asciidoc/"))
        setBaseDir(file("src/docs/asciidoc/"))
        sources(delegateClosureOf<PatternSet> { include("index.adoc") })
        options(mapOf("doctype" to "book"))
        outputOptions { setBackends(listOf("html5")) }
        attributes = mapOf(
            "includedir" to "$sourceDir/",
            "imagesdir" to "$sourceDir/img",
            "source-highlighter" to "prettify",
            "rootdir" to project.rootProject.projectDir.absolutePath,
            "allow-url-read" to "",
            "toc" to "left",
            "icons" to "font",
            "encoding" to "utf-8",
            "sectlink" to "true",
            "sectanchors" to "true",
            "numbered" to "true",
            "linkattrs" to "true",
            "linkcss" to "true",
            "project-title" to "KRis -- Library for reading/writing RIS files",
            "project-inception-year" to "2017",
            "project-copyright-year" to Year.now().value,
            "project-author" to "Gianluca Colaianni, Urs Joss",
            "project-url" to "https://github.com/ursjoss/KRis",
            "project-sccm" to "https://github.com/ursjoss/KRis.git",
            "project-issue-tracker" to "https://github.com/ursjoss/KRis/issues",
            "project-group" to project.group,
            "project-version" to project.version,
            "project-name" to project.rootProject.name,
            "gradle-version" to project.gradle.gradleVersion,
        )
    }

    val asciidoctor by existing
    val createGuide = register<Copy>("createGuide") {
        group = "Documentation"
        description = "Creates an Asciidoctor based guide."
        from(project.tasks.asciidoctor.get().outputDir)
        destinationDir = builtGuideDir
        dependsOn(asciidoctor)
        dependsOn(rootProject.tasks.named("dokkaHtmlMultiModule"))
        dependsOn(rootProject.tasks.named("dokkaJavadocCollector"))
    }
    withType<GitPublishCommit> {
        dependsOn(createGuide)
    }
}

afterEvaluate {
    val dokkaOutputDir = rootProject.buildDir.resolve("dokka")
    val htmlMultiModuleOutputDir = dokkaOutputDir.resolve("htmlMultiModule")
    val htmlJavadocOutputDir = dokkaOutputDir.resolve("javadocCollector")
    gitPublish {
        repoUri.set("https://github.com/ursjoss/KRis.git")
        branch.set("gh-pages")
        contents {
            project.tasks.findByName("createGuide")?.outputs?.files?.let {
                from(it)
            }
            from(htmlMultiModuleOutputDir) {
                into("kapi")
            }
            from(htmlJavadocOutputDir) {
                into("javadoc")
            }
        }
        preserve {
            include(htmlMultiModuleOutputDir.absolutePath)
        }
        commitMessage.set("Publish guide for ${project.version}")
    }
}
