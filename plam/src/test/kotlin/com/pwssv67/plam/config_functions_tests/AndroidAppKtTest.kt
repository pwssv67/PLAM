package com.pwssv67.plam.config_functions_tests

import com.android.build.api.dsl.ApplicationExtension
import com.pwssv67.plam.DependencyWrapper
import com.pwssv67.plam.ModuleType
import com.pwssv67.plam.androidApp
import com.pwssv67.plam.utils.PluginsUtilsKtTest
import org.gradle.api.Action
import org.gradle.api.Project
import org.junit.Test
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify


class AndroidAppKtTest: ConfigFunctionBaseTest() {
    override val moduleType: ModuleType = ModuleType.BaseModuleType.App
    override fun Project.configure(dependencyList: List<DependencyWrapper>) {
        androidApp(dependencyList)
    }

    @Test
    fun `check that plugins are applied`() {
        project.androidApp()

        with(PluginsUtilsKtTest) {
            project.checkPluginsForAppAreApplied()
        }
    }

    @Test
    fun `check that android extensions action is applied from project configuration field`() {

        lateinit var appConfigAction: Action<ApplicationExtension>
        setConfiguration {
            appConfigAction = this.appConfig
        }

        project.androidApp()

        verify(project.extensions, atLeastOnce()).configure("android", appConfigAction)
    }

    @Test
    fun `check that android extensions action is applied from input lambda`() {
        val action = Action<ApplicationExtension> {}
        project.androidApp(configure = action)

        verify(project.extensions, atLeastOnce()).configure("android", action)
    }

}
