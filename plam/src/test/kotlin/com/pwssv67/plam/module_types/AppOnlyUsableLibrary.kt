package com.pwssv67.plam.module_types

import com.pwssv67.plam.CustomAppModule
import com.pwssv67.plam.CustomLibraryModule
import com.pwssv67.plam.ModuleType

class AppOnlyUsableLibrary: CustomLibraryModule() {
    override fun canDependOn(other: ModuleType): Boolean {
        return when (other) {
            is AppOnlyUsableLibrary,
            is ModuleType.BaseModuleType.Library -> true
            else -> false
        }
    }

    override fun canBeDependableByOther(other: ModuleType): Boolean {
        return when (other) {
            is ModuleType.BaseModuleType.App,
            is CustomAppModule -> true
            else -> false
        }
    }
}