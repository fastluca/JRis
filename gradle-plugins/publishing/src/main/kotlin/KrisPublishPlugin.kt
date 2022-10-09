import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.plugins.signing.SigningExtension

@Suppress("unused")
class KrisPublishPlugin : Plugin<Project> {

    private val gitUrl = "https://github.com/ursjoss/KRis"

    override fun apply(target: Project) {
        target.plugins.apply("org.gradle.maven-publish")
        target.plugins.apply("org.gradle.java-library")
        target.plugins.apply("org.gradle.signing")

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

        target.extensions.configure<SigningExtension> {
            val signingKey = target.providers.environmentVariable("GPG_SIGNING_KEY")
            val signingPassphrase = target.providers.environmentVariable("GPG_SIGNING_PASSPHRASE")
            if (signingKey.isPresent && signingPassphrase.isPresent) {
                useInMemoryPgpKeys(signingKey.get(), signingPassphrase.get())
                val extension = target.extensions.getByName("publishing") as PublishingExtension
                sign(extension.publications)
            }
        }

        target.extensions.configure<JavaPluginExtension> {
            withSourcesJar()
            withJavadocJar()
        }
    }
}
