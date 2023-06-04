package com.pwssv67.plam.utils

import com.pwssv67.plam.PlamPlugin
import com.pwssv67.plam.test_utils.MockProjectTest
import org.gradle.api.Project
import org.junit.Test
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify

class PluginsUtilsKtTest: MockProjectTest() {
    @Test
    fun applyDefaultPluginsForApp() {
        project.applyDefaultPlugins(application = true)

        project.checkPluginsForAppAreApplied()
    }

    @Test
    fun applyDefaultPluginsForLibrary() {
        project.applyDefaultPlugins(application = false)

        project.checkPluginsForLibraryAreApplied()
    }


    companion object {
        fun Project.checkPluginsForAppAreApplied() {
            checkDefaultPluginsAreApplied()
            checkAppPluginApplied()
        }

        fun Project.checkPluginsForLibraryAreApplied() {
            checkDefaultPluginsAreApplied()
            checkLibraryPluginApplied()
        }

        private fun Project.checkDefaultPluginsAreApplied() {
            verify(this.pluginManager, atLeastOnce()).apply(Plugins.kotlinAndroid)
            verify(this.pluginManager, atLeastOnce()).apply(PlamPlugin::class.java)
        }

        private fun Project.checkAppPluginApplied() {
            verify(this.pluginManager, atLeastOnce()).apply(Plugins.androidApplication)
        }

        private fun Project.checkLibraryPluginApplied() {
            verify(this.pluginManager, atLeastOnce()).apply(Plugins.androidLibrary)
        }
    }
}