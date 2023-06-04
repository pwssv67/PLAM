package com.pwssv67.plam.config_functions_tests

import com.android.build.api.dsl.LibraryExtension
import com.pwssv67.plam.utils.PluginsUtilsKtTest
import org.gradle.api.Action
import org.junit.Test
import org.mockito.Mockito


abstract class CommonLibraryConfigFunctionTest : ConfigFunctionBaseTest() {

    @Test
    fun `check that library plugins are applied`() {
        project.configure()

        with(PluginsUtilsKtTest) {
            project.checkPluginsForLibraryAreApplied()
        }
    }

    @Test
    fun `check that android library extensions action is applied from project configuration field`() {

        lateinit var moduleConfigAction: Action<LibraryExtension>
        setConfiguration {
            moduleConfigAction = this.moduleConfig
        }

        project.configure()

        Mockito.verify(project.extensions, Mockito.atLeastOnce()).configure("android", moduleConfigAction)
    }
}