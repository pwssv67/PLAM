package com.pwssv67.plam

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import com.pwssv67.plam.utils.applyDefaultPlugins
import com.pwssv67.plam.utils.getExtensionByType
import org.gradle.api.plugins.ExtensionAware

fun Project.androidLibrary(dependencyList: List<DependencyWrapper> = emptyList(), configure: Action<LibraryExtension> = Action {}) {
    configureLibraryModule(dependencyList, configure)

    this.extensions.extraProperties.set(ModuleType.KEY, ModuleType.Library)
}

fun Project.androidApi(dependencyList: List<DependencyWrapper> = emptyList(), configure: Action<LibraryExtension> = Action {}) {
    configureLibraryModule(dependencyList, configure)

    this.extensions.extraProperties.set(ModuleType.KEY, ModuleType.FeatureAPI)
}

fun Project.androidImpl(dependencyList: List<DependencyWrapper> = emptyList(), configure: Action<LibraryExtension> = Action {}) {
    configureLibraryModule(dependencyList, configure)

    this.extensions.extraProperties.set(ModuleType.KEY, ModuleType.FeatureImpl)
}

private fun Project.configureLibraryModule(dependencyList: List<DependencyWrapper>, configure: Action<LibraryExtension> = Action {}) {
    applyDefaultPlugins(application = false)
    val config = getExtensionByType(Configuration::class.java)
    (this as ExtensionAware).extensions.apply {
        configure("android", config.moduleConfig)
        configure("android", configure)
    }

    dependencyList.forEach(::registerDependency)
}