package com.pwssv67.plam

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project

@Suppress("unused")
class Configuration {

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

    fun appConfig(configure: ApplicationExtension.() -> Unit) {
        appConfig = Action { configure.invoke(this) }
    }

    var moduleConfig: Action<LibraryExtension> = Action {
        defaultConfig {
            minSdk = 21
        }
    }
    private set

    fun moduleConfig(configure: LibraryExtension.() -> Unit) {
        moduleConfig = Action { configure.invoke(this) }
    }
}

@Suppress("unused")
fun Project.configure(block: Configuration.() -> Unit) {
    val configuration = Configuration()
    configuration.block()
    extensions.add(CONFIG_EXT_NAME, configuration)
}

const val CONFIG_EXT_NAME = "plamConfigurationExtension"