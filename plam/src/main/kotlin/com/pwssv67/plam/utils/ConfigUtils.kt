package com.pwssv67.plam.utils

import org.gradle.api.Project
import org.gradle.api.UnknownDomainObjectException

fun <T> Project.getExtensionByType(type: Class<T>): T {
    val ext = project.extensions.findByType(type)

    if (ext != null) return ext

    if (rootProject != project) {
        return rootProject.getExtensionByType(type)
    } else {
        throw UnknownDomainObjectException("Configuration object is not found. Have you added \"configuration{}\" block to root build.gradle.kts?")
    }
}