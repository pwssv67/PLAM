package com.pwssv67.plam

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.pwssv67.plam.utils.applyDefaultPlugins
import com.pwssv67.plam.utils.getExtensionByType
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import java.lang.ClassCastException

/**
 * Declaration of an android application with custom module type, descendant of [CustomAppModule]
 * Configuration from [Configuration.appConfig] will be applied ***BEFORE*** the [configure], so you can override values here
 *
 * Example:
 * ```kotlin
 * customModule(
 *      module = SomeCustomAppModule(),
 *      dependencyList = listOf(impl(Deps.androidX)) + composeLibs
 * ) {
 *      namespace = "com.example.custom_app"
 *
 *      defaultConfig {
 *          applicationId += "custom_app"
 *      }
 * }
 * ```
 *
 * @param module custom module itself.
 * @param dependencyList list of dependencies for this module. See [impl], [api], [testImpl], etc.
 * @param configure lambda or [Action] with additional configuration for this specific module.
 */
fun Project.customModule(
    module: CustomAppModule,
    dependencyList: List<DependencyWrapper> = emptyList(),
    configure: Action<ApplicationExtension> = Action {}
) {
    applyDefaultPlugins(true)

    val myConfig = getExtensionByType(Configuration::class.java)

    (this as? ExtensionAware)?.extensions?.run {
        //set default config
        configure("android", myConfig.appConfig)
        // apply custom config from configure lambda
        configure("android", configure)
    } ?: throw ClassCastException("Can't cast project of class ${this::class.simpleName} to \"org.gradle.api.plugins.ExtensionAware\"/\nNormally this shouldn't be a problem, please add a bug to https://github.com/pwssv67/TODO")

    dependencyList.forEach(::registerDependency)
    //add type label to module
    this.extensions.extraProperties.set(ModuleType.KEY, module)
}

/**
 * Declaration of an android library with custom module type, descendant of [CustomLibraryModule]
 * Configuration from [Configuration.moduleConfig] will be applied ***BEFORE*** the [configure], so you can override values here
 *
 * Example:
 * ```kotlin
 * customModule(
 *      module = SomeCustomLibModule(),
 *      dependencyList = listOf(impl(Deps.androidX)) + composeLibs
 * ) {
 *      namespace = "com.example.custom_lib"
 *
 *      defaultConfig {
 *          applicationId += "custom_lib"
 *      }
 * }
 * ```
 *
 * @param module custom module itself.
 * @param dependencyList list of dependencies for this module. See [impl], [api], [testImpl], etc.
 * @param configure lambda or [Action] with additional configuration for this specific module.
 */
fun Project.customModule(
    module: CustomLibraryModule,
    dependencyList: List<DependencyWrapper> = emptyList(),
    configure: Action<LibraryExtension> = Action {}
) {
    configureLibraryModule(dependencyList, configure)
    this.extensions.extraProperties.set(ModuleType.KEY, module)
}