package com.pwssv67.plam.module_types

import com.pwssv67.plam.ModuleType
import org.junit.Test

class CustomModuleTest {
    @Test
    fun `AllConsumingCustomAppModule can consume any other one`() {
        val baseModules = ModuleType.BaseModuleType.values()
        val customAppModule = AllConsumingCustomAppModule()

        baseModules.forEach { baseModule ->
            assert(customAppModule.canDependOn(baseModule))
        }
    }

    @Test
    fun `AppOnlyUsableLibrary can be consumed in app and custom app module`() {
        val baseAppModule = ModuleType.BaseModuleType.App
        val customAppModule = AllConsumingCustomAppModule()

        val appOnlyUsableLibrary = AppOnlyUsableLibrary()

        assert(baseAppModule.canDependOn(appOnlyUsableLibrary))
        assert(baseAppModule.canDependOn(customAppModule))
    }
}