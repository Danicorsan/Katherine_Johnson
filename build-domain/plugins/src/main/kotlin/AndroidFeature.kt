
import app.builddomain.plugins.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeature : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("app.android.library")
                apply("app.android.hilt")
            }

            dependencies {
                add("implementation", project(":base:ui"))
                add("implementation", project(":base:features"))
                add("implementation", project(":base:utils"))
                add("implementation", project(":domain:ddd"))
                add("implementation", project(":domain:navigation"))

                add("implementation", libs.findLibrary("androidx.hilt.navigationcompose").get())
                add(
                    "implementation",
                    libs.findLibrary("androidx-lifecycle-viewmodel-compose").get()
                )
                add("implementation", libs.findLibrary("kotlinx.datetime").get())

                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("kotlin.coroutines.test").get())
                add("testImplementation", libs.findLibrary("robolectric").get())
                add("testImplementation", libs.findLibrary("truth").get())
                add("testImplementation", libs.findLibrary("androidx-test-junit").get())
            }
        }
    }
}