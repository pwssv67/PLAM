package com.pwssv67.plam.config_functions_tests

import com.android.build.api.dsl.LibraryExtension
import com.pwssv67.plam.DependencyWrapper
import com.pwssv67.plam.ModuleType
import com.pwssv67.plam.androidApi
import org.gradle.api.Action
import org.gradle.api.Project
import org.junit.Test
import org.mockito.Mockito

class ModuleAPIConfigFunctionTest: CommonLibraryConfigFunctionTest() {
    override val moduleType: ModuleType = ModuleType.FeatureAPI
    override fun Project.configure(dependencyList: List<DependencyWrapper>) {
        androidApi(dependencyList)
    }

    @Test
    fun `check that android library extensions action is applied from input lambda`() {
        val action = Action<LibraryExtension> {}
        project.androidApi(configure = action)

        Mockito.verify(project.extensions, Mockito.atLeastOnce()).configure("android", action)
    }
}