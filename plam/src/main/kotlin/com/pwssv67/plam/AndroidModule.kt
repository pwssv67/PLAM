package com.pwssv67.plam

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import com.pwssv67.plam.utils.applyDefaultPlugins
import com.pwssv67.plam.utils.getExtensionByType
import org.gradle.api.plugins.ExtensionAware

/**
 * Main configuration function, configuring library module, with module type of [ModuleType.BaseModuleType.Library].
 *
 * Configuration from [Configuration.moduleConfig] will be applied ***BEFORE*** the [configure], so you can override values here
 *
 * @param dependencyList list of dependencies for this module. See [impl], [api], [testImpl], etc.
 * @param configure lambda or [Action] with additional configuration for this specific module.
 */
fun Project.androidLibrary(dependencyList: List<DependencyWrapper> = emptyList(), configure: Action<LibraryExtension> = Action {}) {
    configureLibraryModule(dependencyList, configure)

    this.extensions.extraProperties.set(ModuleType.KEY, ModuleType.BaseModuleType.Library)
}

/**
 * Main configuration function, configuring library module, with module type of [ModuleType.BaseModuleType.FeatureAPI].
 *
 * Configuration from [Configuration.moduleConfig] will be applied ***BEFORE*** the [configure], so you can override values here
 *
 * @param dependencyList list of dependencies for this module. See [impl], [api], [testImpl], etc.
 * @param configure lambda or [Action] with additional configuration for this specific module.
 */
fun Project.androidApi(dependencyList: List<DependencyWrapper> = emptyList(), configure: Action<LibraryExtension> = Action {}) {
    configureLibraryModule(dependencyList, configure)

    this.extensions.extraProperties.set(ModuleType.KEY, ModuleType.BaseModuleType.FeatureAPI)
}

/**
 * Main configuration function, configuring library module, with module type of [ModuleType.BaseModuleType.FeatureImpl].
 *
 * Configuration from [Configuration.moduleConfig] will be applied ***BEFORE*** the [configure], so you can override values here
 *
 * @param dependencyList list of dependencies for this module. See [impl], [api], [testImpl], etc.
 * @param configure lambda or [Action] with additional configuration for this specific module.
 */
fun Project.androidImpl(dependencyList: List<DependencyWrapper> = emptyList(), configure: Action<LibraryExtension> = Action {}) {
    configureLibraryModule(dependencyList, configure)

    this.extensions.extraProperties.set(ModuleType.KEY, ModuleType.BaseModuleType.FeatureImpl)
}

internal fun Project.configureLibraryModule(dependencyList: List<DependencyWrapper>, configure: Action<LibraryExtension> = Action {}) {
    applyDefaultPlugins(application = false)
    val config = getExtensionByType(Configuration::class.java)
    (this as ExtensionAware).extensions.apply {
        configure("android", config.moduleConfig)
        configure("android", configure)
    }

    dependencyList.forEach(::registerDependency)
}