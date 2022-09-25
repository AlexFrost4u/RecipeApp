import com.alexfrost.recipeapp.configureAndroidXml
import com.alexfrost.recipeapp.configureKotlinAndroid
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class AndroidApplicationXmlPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        pluginManager.apply("com.android.application")
        pluginManager.apply("kotlin-android")

        val extension = extensions.getByType<BaseAppModuleExtension>()

        configureAndroidXml(extension)
        configureKotlinAndroid(extension)
    }
}
