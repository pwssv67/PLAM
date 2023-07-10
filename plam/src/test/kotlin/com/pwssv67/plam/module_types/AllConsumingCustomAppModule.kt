package com.pwssv67.plam.module_types

import com.pwssv67.plam.CustomAppModule
import com.pwssv67.plam.ModuleType

class AllConsumingCustomAppModule: CustomAppModule() {
    override fun canBeDependableByOther(other: ModuleType): Boolean {
        return when (other) {
            is CustomAppModule,
            is ModuleType.BaseModuleType.App -> true
            else -> false
        }
    }

    override fun canDependOn(other: ModuleType) = true
}