import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

@Suppress("TooManyFunctions", "MaximumLineLength", "MemberVisibilityCanBePrivate", "SpellCheckingInspection")
object Lib {

    private const val kotlinVersion = "1.3.61"

    fun kotlin(module: String) = Dep("org.jetbrains.kotlin:kotlin-$module", kotlinVersion)
    fun kotlinx(module: String) = Dep("org.jetbrains.kotlinx:kotlinx-$module", "1.3.2")

    fun rxjava2() = Dep("io.reactivex.rxjava2:rxjava", "2.2.15")
    fun rxkotlin() = Dep("io.reactivex.rxjava2:rxkotlin", "2.4.0")

    fun junit5(module: String = "") =
        Dep("org.junit.jupiter", "junit-jupiter${if (module.isNotBlank()) "-$module" else ""}", "5.5.2")

    fun spek(module: String) = Dep("org.spekframework.spek2", "spek-$module", "2.0.8")
    fun mockk() = Dep("io.mockk", "mockk", "1.9.3")
    fun kluent() = Dep("org.amshove.kluent", "kluent", "1.58")
    fun assertJ() = Dep("org.assertj", "assertj-core", "3.14.0")
}

class Dep(val group: String, val name: String, val version: String? = null) {
    val id: String
        get() {
            val versionPart = if (version != null) ":$version" else ""
            return "$group:$name$versionPart"
        }
}

fun DependencyHandler.implementation(dependencyNotation: Dep): Dependency? =
    add("implementation", dependencyNotation.id)

fun DependencyHandler.testImplementation(dependencyNotation: Dep): Dependency? =
    add("testImplementation", dependencyNotation.id)

fun DependencyHandler.testRuntimeOnly(dependencyNotation: Dep): Dependency? =
    add("testRuntimeOnly", dependencyNotation.id)

fun DependencyHandler.integrationTestImplementation(dependencyNotation: Dep): Dependency? =
    add("integrationTestImplementation", dependencyNotation.id)

fun DependencyHandler.integrationTestRuntimeOnly(dependencyNotation: Dep): Dependency? =
    add("integrationTestRuntimeOnly", dependencyNotation.id)
