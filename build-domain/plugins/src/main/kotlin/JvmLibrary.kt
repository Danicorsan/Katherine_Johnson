import app.builddomain.plugins.configureKotlinJvm
import app.builddomain.plugins.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class JvmLibrary : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()

            dependencies {
                add("implementation", libs.findLibrary("truth").get())

                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("junit-jupiter").get())
            }
        }
    }
}