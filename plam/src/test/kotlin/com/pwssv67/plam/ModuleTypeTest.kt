package com.pwssv67.plam

import com.pwssv67.plam.ModuleType.*
import org.junit.Test


class ModuleTypeTest {
    @Test
    fun `check that App module can depend to any`() {
        ModuleType.values()
            .forEach { otherModule -> assert(App.canDependOn(otherModule)) {"App should be able to depend on ${otherModule::class.simpleName}"} }
    }

    @Test
    fun `check that Library module can depend on other libraries`() {
        assert(Library.canDependOn(Library))
    }

    @Test
    fun `check that Library module cannot depend on other types`() {
        ModuleType.values()
            .filter { it !is Library }
            .forEach { otherModule -> assert(Library.canDependOn(otherModule).not()) {"Library should not be able to depend on ${otherModule::class.simpleName}"} }
    }

    @Test
    fun `check that Feature API module can depend on libraries`() {
        assert(FeatureAPI.canDependOn(Library))
    }

    @Test
    fun `check that feature API cannot depend on feature impl or app`() {
        assert(FeatureAPI.canDependOn(App).not())
        assert(FeatureAPI.canDependOn(FeatureImpl).not())
    }

    @Test
    fun `check that feature impl can depend on feature api`() {
        assert(FeatureImpl.canDependOn(FeatureAPI))
    }

    @Test
    fun `check that feature impl can depend on library`() {
        assert(FeatureImpl.canDependOn(Library))
    }

    @Test
    fun `check that feature impl cannot depend on feature impl or app`() {
        assert(FeatureImpl.canDependOn(App).not())
        assert(FeatureImpl.canDependOn(FeatureImpl).not())
    }
}