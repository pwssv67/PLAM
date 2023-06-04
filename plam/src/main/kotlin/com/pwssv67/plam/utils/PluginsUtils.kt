package com.pwssv67.plam.utils

import com.pwssv67.plam.PlamPlugin
import org.gradle.api.Project

internal fun Project.applyDefaultPlugins(application: Boolean) {
    pluginManager.apply(Plugins.kotlinAndroid)
    if (application) {
        pluginManager.apply(Plugins.androidApplication)
    } else {
        pluginManager.apply(Plugins.androidLibrary)
    }
    pluginManager.apply(PlamPlugin::class.java)
}

internal object Plugins {
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
}