package com.pwssv67.plam.config_functions_tests

import com.pwssv67.plam.DependencyWrapper
import com.pwssv67.plam.ModuleType
import com.pwssv67.plam.test_utils.MockProjectTest
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

abstract class ConfigFunctionBaseTest : MockProjectTest() {

    abstract fun Project.configure(dependencyList: List<DependencyWrapper> = listOf())

    internal abstract val moduleType: ModuleType

    @Before
    fun `set up extra mocks`() {
        `set up rootProject mock`()
        `set up configuration mock`()
        `set up extra properties mock`()
    }

    @Test
    fun `check that android extensions are called at least once`() {
        project.configure()

        verify(project.extensions, atLeastOnce()).configure<Any>(eq("android"), any())
    }

    @Test
    fun `check that module type is correct`() {
        project.configure()

        verify(project.extra, atLeastOnce()).set(ModuleType.KEY, moduleType)
    }

    private fun `set up configuration mock`() {
        setConfiguration {  }
    }

    private fun `set up extra properties mock`() {
        `when`(project.extensions.extraProperties).thenReturn(mock())
    }

    private fun `set up rootProject mock`() {
        val rootProject: Project = mock()
        `when`(project.rootProject).thenReturn(rootProject)
        `when`(project.rootProject.project).thenReturn(rootProject)
        `when`(rootProject.extensions).thenReturn(mock())
    }
}