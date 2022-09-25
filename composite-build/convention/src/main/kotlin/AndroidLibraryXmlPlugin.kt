import com.alexfrost.recipeapp.configureAndroidXml
import com.alexfrost.recipeapp.configureKotlinAndroid
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class AndroidLibraryXmlPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply(plugin = "com.android.library")
        apply(plugin = "kotlin-android")

        val extension = extensions.getByType<LibraryExtension>()

        configureAndroidXml(extension)
        configureKotlinAndroid(extension)
    }
}
