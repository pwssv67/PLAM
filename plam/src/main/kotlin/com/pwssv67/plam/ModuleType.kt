package com.pwssv67.plam

@Suppress("MemberVisibilityCanBePrivate")
/**
 * Type of the module. It defines on which modules it could or could not depend.
 *
 * @see [possibleDependencies]
 */
sealed class ModuleType {

    fun canDependOn(other: ModuleType): Boolean {
        return possibleDependencies().contains(other)
    }

    fun possibleDependencies(): List<ModuleType> {
        return when (this) {
            App -> any()
            Library -> only(Library)
            FeatureAPI -> only(Library)
            FeatureImpl -> only(FeatureAPI, Library)
        }
    }

    object App : ModuleType()
    object Library : ModuleType()
    object FeatureAPI : ModuleType()
    object FeatureImpl : ModuleType()

    companion object {
        const val KEY = "ModuleType"
        fun values(): Array<ModuleType> {
            return arrayOf(App, Library, FeatureAPI, FeatureImpl)
        }
    }
}

private fun only(vararg types: ModuleType): List<ModuleType> {
    return types.toList()
}

private fun any(): List<ModuleType> {
    return ModuleType.values().toList()
}