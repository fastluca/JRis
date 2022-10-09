import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.existing

@Suppress("unused")
class KrisPublishPlugin : Plugin<Project> {

    private val gitUrl = "https://github.com/ursjoss/KRis"

    override fun apply(target: Project) {
        target.plugins.apply("org.gradle.maven-publish")
        target.plugins.apply("org.gradle.java-library")

        target.extensions.configure<PublishingExtension> {
            publications {
                create<MavenPublication>("kris") {
                    groupId = target.project.group.toString()
                    artifactId = target.project.name
                    version = target.project.version.toString()

                    from(target.components.findByName("java"))

                    pom {
                        name.set(target.rootProject.name)
                        description.set("Kotlin library for importing/exporting bibliographic records in RIS format")
                        url.set(gitUrl)
                        scm {
                            url.set(gitUrl)
                        }
                        ciManagement {
                            url.set("$gitUrl/actions")
                        }
                        issueManagement {
                            url.set("$gitUrl/issues")
                        }
                        licenses {
                            license {
                                name.set("MIT")
                                url.set("https://opensource.org/licenses/mit-license.php")
                                distribution.set("repo")
                            }
                        }
                        developers {
                            developer {
                                id.set("fastluca")
                                name.set("Gianluca Colaianni")
                                roles.add("developer")
                            }
                            developer {
                                id.set("ursjoss")
                                name.set("Urs Joss")
                                roles.add("developer")
                            }
                        }
                    }
                }
            }
        }

        target.extensions.configure<JavaPluginExtension> {
            withSourcesJar()
            withJavadocJar()
        }
    }
}
