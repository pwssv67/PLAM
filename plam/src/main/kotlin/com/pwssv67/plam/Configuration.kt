package com.pwssv67.plam

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project

/**
 * Configuration container for all the module. To set configuration, see [plam]
 */
class Configuration {

    /**
     * Default configuration for any module, set up with [androidApp] or [customModule] with [CustomAppModule]
     */
    var appConfig: Action<ApplicationExtension> = Action {
        defaultConfig {
            applicationId = "com.sample_company.sample_app"
            minSdk = 21
            targetSdk = 30
            versionCode = 1
            versionName = "1.0"
        }
    }
    private set

    /**
     * Default configuration for any module, set up with [androidApp] or [customModule] with [CustomAppModule]
     */
    fun appConfig(configure: ApplicationExtension.() -> Unit) {
        appConfig = Action { configure.invoke(this) }
    }

    /**
     * Default configuration for any module, set up with [androidLibrary], [androidApi], [androidImpl] or [customModule] with [CustomLibraryModule]
     */
    var moduleConfig: Action<LibraryExtension> = Action {
        defaultConfig {
            minSdk = 21
        }
    }
    private set

    /**
     * Default configuration for any module, set up with [androidLibrary], [androidApi], [androidImpl] or [customModule] with [CustomLibraryModule]
     */
    fun moduleConfig(configure: LibraryExtension.() -> Unit) {
        moduleConfig = Action { configure.invoke(this) }
    }
}

/**
 * Function to configure PLAM, should be used in **project**'s build.gradle.kts
 */
fun Project.plam(block: Configuration.() -> Unit) {
    val configuration = Configuration()
    configuration.block()
    extensions.add(CONFIG_EXT_NAME, configuration)
}

const val CONFIG_EXT_NAME = "plamConfigurationExtension"