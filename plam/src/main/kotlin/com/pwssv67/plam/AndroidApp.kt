package com.pwssv67.plam

import com.android.build.api.dsl.ApplicationExtension
import com.pwssv67.plam.utils.applyDefaultPlugins
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import java.lang.ClassCastException


fun Project.androidApp(
    dependencyList: List<DependencyWrapper> = emptyList(),
    configure: Action<ApplicationExtension> = Action {}
) {
    applyDefaultPlugins(true)

    val myConfig = project.extensions.getByType(Configuration::class.java)

    (this as? ExtensionAware)?.extensions?.run {
        //set default config
        configure("android", myConfig.appConfig)
        // apply custom config from configure lambda
        configure("android", configure)
    } ?: throw ClassCastException("Can't cast project of class ${this::class.simpleName} to \"org.gradle.api.plugins.ExtensionAware\"/\nNormally this shouldn't be a problem, please add a bug to https://github.com/pwssv67/TODO")

    dependencyList.forEach(::registerDependency)
    //add type label to module
    this.extensions.extraProperties.set(ModuleType.KEY, ModuleType.App)
}