package com.pwssv67.plam

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.configurationcache.extensions.capitalized
import java.lang.IllegalStateException

data class DependencyWrapper(val config: String, val dep: Any, val registerAsPlatform: Boolean = false)

internal fun Project.registerDependency(dependency: DependencyWrapper) {
    var config = ""

    fun addConfig(configToAdd: String) {
        if (config.isNotEmpty()) {
            config += configToAdd.capitalized()
        } else {
            config = configToAdd
        }
    }

    var dep: Any = dependency
    var registeredAsPlatform = false //flag ONLY for accurate logging

    while (dep is DependencyWrapper) {
        addConfig(dep.config)
        if (dep.registerAsPlatform) {
            dep = registerAsPlatform(dep) // this is final step, because platform dependencies are registered in different way. You can see DefaultDependencyHandler.platform() method for details.
            registeredAsPlatform = true
            break
        }
        dep = dep.dep
    }

    if (registeredAsPlatform) {
        val castDep = dep as? Dependency
        // we can't use dep.toString() because it will give too much of information instead of "group:name:version"
         castDep?.let { logger.info("Register platform dependency: $config \"${it.group}:${it.name}:${it.version}\"") }
    } else {
        logger.info("Register dependency: $config \"$dep\"")
    }

    dependencies.add(config, dep)
}

private fun Project.registerAsPlatform(depTest: DependencyWrapper): Any {
    if (depTest.dep !is DependencyWrapper) {
        val dep = depTest.dep
        val registeredDep: Any? = when (dep) {
            is Provider<*> -> registerProvider(dep)
            else -> dependencies.platform(dep)
        }
        registeredDep ?: throw IllegalStateException("there is problem with registering ${depTest.dep} as a platform dependency. Enabling Gradle's debugging option might help identifying the problem,")
        return registeredDep
    } else {
        throw IllegalArgumentException("Only simple dependencies can be registered as platform dependencies. You can reorder your modifiers to make platform dependency inmost.")
    }
}

@SuppressWarnings("unchecked")
private fun Project.registerProvider(dep: Provider<*>): Any? {
    if (dep.orNull is MinimalExternalModuleDependency) {
        val castDep = dep as Provider<MinimalExternalModuleDependency>
        return dependencies.platform(castDep)
    } else {
        throw IllegalArgumentException("Provider with generic type ${dep.orNull} isn't currently supported as a dependency")
    }
}