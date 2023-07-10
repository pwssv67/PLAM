package com.pwssv67.plam.config_functions_tests

import com.android.build.api.dsl.LibraryExtension
import com.pwssv67.plam.DependencyWrapper
import com.pwssv67.plam.ModuleType
import com.pwssv67.plam.androidImpl
import org.gradle.api.Action
import org.gradle.api.Project
import org.junit.Test
import org.mockito.Mockito

class ModuleImplConfigFunctionTest: CommonLibraryConfigFunctionTest() {
    override val moduleType: ModuleType = ModuleType.BaseModuleType.FeatureImpl
    override fun Project.configure(dependencyList: List<DependencyWrapper>) {
        androidImpl(dependencyList)
    }

    @Test
    fun `check that android library extensions action is applied from input lambda`() {
        val action = Action<LibraryExtension> {}
        project.androidImpl(configure = action)

        Mockito.verify(project.extensions, Mockito.atLeastOnce()).configure("android", action)
    }
}