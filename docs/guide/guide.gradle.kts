plugins {
    id("org.kordamp.gradle.guide")
    id("org.ajoberstar.git-publish")
}

afterEvaluate {
    val createGuide by tasks.existing

    gitPublish {
        repoUri.set("git@github.com:ursjoss/JRis.git")
        branch.set("gh-pages")

        contents {
            from(createGuide.get().outputs.files)
        }
        commitMessage.set("Publish guide for $version")
    }

    tasks.gitPublishCommit.get().dependsOn(createGuide)
}