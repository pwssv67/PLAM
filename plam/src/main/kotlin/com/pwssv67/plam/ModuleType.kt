package com.pwssv67.plam

@Suppress("MemberVisibilityCanBePrivate")
/**
 * Type of the module. It defines on which modules it could or could not depend.
 * Predefined module types are in the [BaseModuleType]
 * If you want to create your own module - use [CustomAppModule] and [CustomLibraryModule]
 *
 * @see [canDependOn]
 */
sealed interface ModuleType {

    fun canDependOn(other: ModuleType): Boolean

    /**
     * Predefined module types
     */
    sealed class BaseModuleType: ModuleType {
        /**
         * Application module. Can depend on any other, can be only be used as dependency in other App modules
         */
        object App : BaseModuleType()

        /**
         * Library module. Can only depend on other libraries (external and internal), can be used as a dependency by any other module
         */
        object Library : BaseModuleType()

        /**
         * Feature API module. Can only depend on libraries, can be used as a dependency only by [FeatureImpl] and [App]
         */
        object FeatureAPI : BaseModuleType()

        /**
         * Feature Implementation module. Can only depend on libraries and [FeatureAPI], can be used as a dependency only by [App]
         */
        object FeatureImpl : BaseModuleType()

        override fun canDependOn(other: ModuleType): Boolean {
            return when (other) {
                is BaseModuleType -> possibleDependencies().contains(other)
                is CustomModuleType -> other.canBeDependableByOther(this)
            }
        }

        fun possibleDependencies(): List<BaseModuleType> {
            return when (this) {
                App -> any()
                Library -> only(Library)
                FeatureAPI -> only(Library)
                FeatureImpl -> only(FeatureAPI, Library)
            }
        }

        private fun only(vararg types: BaseModuleType): List<BaseModuleType> {
            return types.toList()
        }

        private fun any(): List<BaseModuleType> {
            return values()
        }

        companion object {
            fun values(): List<BaseModuleType> {
                return listOf(App, Library, FeatureAPI, FeatureImpl)
            }
        }
    }

    companion object {
        const val KEY = "ModuleType"
    }
}

private interface CustomModuleType: ModuleType {
    fun canBeDependableByOther(other: ModuleType): Boolean
}

/**
 * Base for custom modules, built with Android App plugin.
 */
abstract class CustomAppModule: CustomModuleType

/**
 * Base for custom modules, built with Android Library plugin.
 */
abstract class CustomLibraryModule: CustomModuleType
