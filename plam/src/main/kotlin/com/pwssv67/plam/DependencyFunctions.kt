@file:Suppress("unused")

package com.pwssv67.plam

//region basics

/**
 * This function is used to wrap dependency notation and add some modifiers to it.
 *
 * @param prefix modifier to add to dependency notation
 * @return DependencyWrapper - wrapper for dependency notation
 * @see DependencyWrapper
 * @see [debug]
 */
fun prefixDep(prefix: String, dependencyNotation: Any): DependencyWrapper = DependencyWrapper(prefix, dependencyNotation)

/**
 * This function is used to wrap dependency notation and add "implementation" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { implementation("...") }`
 *
 * @return DependencyWrapper - wrapper for dependency notation
 * @see DependencyWrapper
 */
fun impl(dependencyNotation: Any): DependencyWrapper = DependencyWrapper(DepTypes.impl, dependencyNotation)

/**
 * This function is used to wrap dependency notation and add "api" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { api("...") }`
 *
 * @return DependencyWrapper - wrapper for dependency notation
 * @see DependencyWrapper
 */
fun api(dependencyNotation: Any): DependencyWrapper = DependencyWrapper(DepTypes.api, dependencyNotation)

/**
 * This function is used to wrap dependency notation and add "compileOnly" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { compileOnly("...") }`
 *
 * @return DependencyWrapper - wrapper for dependency notation
 * @see DependencyWrapper
 */
fun compileOnly(dependencyNotation: Any): DependencyWrapper = DependencyWrapper(DepTypes.compileOnly, dependencyNotation)

/**
 * This function is used to wrap dependency notation and add "kapt" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { annotationProcessor("...") }`
 *
 * @return DependencyWrapper - wrapper for dependency notation
 * @see DependencyWrapper
 */
fun annotationProcessor(dependencyNotation: Any): DependencyWrapper = DependencyWrapper(DepTypes.annotationProcessor, dependencyNotation)

/**
 * This function is used to wrap dependency notation and add "test" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { test("...") }`
 *
 * @return DependencyWrapper - wrapper for dependency notation
 * @see DependencyWrapper
 */
fun test(dependency: DependencyWrapper) = DependencyWrapper(DepTypes.test, dependency)
//endregion

//region flavors
/**
 * This function is used to wrap dependency notation and add flavor modifier to it.
 *
 * Analogue for basic Gradle KTS 
 * ```kotlin
 * dependencies { flavor1Implementation("...") }
 * ```
 * would be
 * ```kotlin
 * flavor("flavor1", impl("..."))
 * ```
 */
fun flavor(flavor: String, dependency: DependencyWrapper): DependencyWrapper = prefixDep(flavor, dependency)

/**
 * This function is used to use [flavor] function with multiple flavors.
 * 
 * You can use it like this:
 * ```kotlin
 *      flavors(listOf("flavor1", "flavor2"), impl("..."))
 * ```
 * and get similar result to
 * ```kotlin
 * dependencies {
 *     flavor1Impl("...")
 *     flavor2Impl("...")
 * }
 * ```
 * 
 * @see flavor
 */
fun flavors(flavors: List<String>, dependency: DependencyWrapper): List<DependencyWrapper> = flavors.map { flavor(it, dependency) }
//endregion

//region utilities
/**
 * This function is used to wrap dependency notation and add "testImplementation" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { testImplementation("...") }`
 *
 * @return DependencyWrapper - wrapper for dependency notation
 * @see DependencyWrapper
 */
fun testImpl(dependencyNotation: Any): DependencyWrapper = test(impl(dependencyNotation))

/**
 * This function is used to wrap dependency notation and add "androidTest" modifier to it.
 * 
 * Analogue for basic Gradle KTS
 * ```kotlin
 * dependencies { androidTestImplementation("...") }
 * ```
 * would be:
 * ```kotlin
 * androidTest(impl("..."))
 * ```
 */
fun androidTest(dependency: DependencyWrapper): DependencyWrapper = prefixDep(DepTypes.android, test(dependency))

/**
 * This function is used to wrap dependency notation and add "androidTestImplementation" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { androidTestImplementation("...") }`
 */
fun androidTestImpl(dependencyNotation: Any): DependencyWrapper = androidTest(impl(dependencyNotation))

/**
 * This function is used to wrap dependency notation and add "debug" modifier to it.
 *
 * Analogue for basic Gradle KTS
 * ```kotlin
 * dependencies { debugImplementation("...") }
 * ```
 * would be:
 * ```kotlin
 * debug(impl("..."))
 * ```
 */
fun debug(dependency: DependencyWrapper): DependencyWrapper = prefixDep(DepTypes.debug, dependency)

/**
 * This function is used to wrap dependency notation and add "release" modifier to it.
 *
 * Analogue for basic Gradle KTS
 * ```kotlin
 * dependencies { releaseImplementation("...") }
 * ```
 * would be:
 * ```kotlin
 * release(impl("..."))
 * ```
 */
fun release(dependency: DependencyWrapper): DependencyWrapper = prefixDep(DepTypes.release, dependency)

/**
 * This function is used to wrap dependency notation and add "debugImplementation" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { debugImplementation("...") }`
 */
fun debugImpl(dependencyNotation: Any): DependencyWrapper = debug(impl(dependencyNotation))

/**
 * This function is used to wrap dependency notation and add "releaseImplementation" modifier to it.
 *
 * Analogue for basic Gradle KTS `dependencies { releaseImplementation("...") }`
 */
fun releaseImpl(dependencyNotation: Any): DependencyWrapper = release(impl(dependencyNotation))
//endregion

/**
 * This function is used to wrap dependency notation and register it as a *platform* dependency.
 *
 * Don't use it with other functions inside! It will not work, and build will fail. Instead, wrap it with other functions.
 *
 * Analogue for basic Gradle KTS `dependencies { platform("...") }`
 */
fun platform(dependencyNotation: Any): DependencyWrapper = DependencyWrapper("", dependencyNotation, true)

@Suppress("DeprecatedCallableAddReplaceWith", "unused") //can't be properly replaced because we have no access to inner function argument. If you know how to specify replacement of a(b(arg)) to b(a(arg)) - please create an issue.
@Deprecated(message = "Don't wrap other custom dependencies with platform. Instead wrap platform() with other functions.", level = DeprecationLevel.ERROR)
fun platform(dependencyNotation: DependencyWrapper): DependencyWrapper = throw IllegalArgumentException("Don't wrap another dependencies with platform()! This isn't supported neither by Gradle nor by plam")

