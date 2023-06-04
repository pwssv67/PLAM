package com.pwssv67.plam.test_utils

import com.pwssv67.plam.Configuration
import org.gradle.api.Project
import org.junit.Before
import org.mockito.Mockito.*

open class MockProjectTest {
    protected lateinit var project: Project

    @Before
    fun setUp() {
        project = createMockProject()
    }

    private fun createMockProject(): Project {
        val mockProject: Project = mock()
        `when`(mockProject.plugins).thenReturn(mock())
        `when`(mockProject.pluginManager).thenReturn(mock())
        `when`(mockProject.extensions).thenReturn(mock())
        `when`(mockProject.logger).thenReturn(mock())
        `when`(mockProject.dependencies).thenReturn(mock())

        `when`(mockProject.project).thenReturn(mockProject)

        return mockProject
    }

    protected fun setConfiguration(configurationBlock: Configuration.() -> Unit) {
        `when`(project.extensions.getByType(Configuration::class.java)).thenReturn(
            Configuration().apply(configurationBlock)
        )
    }
}