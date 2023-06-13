package com.pwssv67.plam

import com.pwssv67.plam.test_utils.MockProjectTest
import org.gradle.api.Transformer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.junit.Test
import org.mockito.Mockito.*
import java.util.function.BiFunction

class DependencyRegisteringTest: MockProjectTest() {

    @Test
    fun `check that impl dependencies are added correctly`() {
        val dependency = impl(DEP_NAME)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.impl, DEP_NAME)
    }

    @Test
    fun `check simple api dependencies`() {
        val dependency = api(DEP_NAME)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.api, DEP_NAME)
    }

    @Test
    fun `check simple compileOnly dependencies`() {
        val dependency = compileOnly(DEP_NAME)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.compileOnly, DEP_NAME)
    }

    @Test
    fun `check simple annotationProcessor dependencies`() {
        val dependency = annotationProcessor(DEP_NAME)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.annotationProcessor, DEP_NAME)
    }

    @Test
    fun `check test deps`() {
        val dependency = test(impl(DEP_NAME))

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.test + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `check test impl dependencies`() {
        val dependency = testImpl(DEP_NAME)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.test + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `android test deps`() {
        val dependency = androidTest(impl(DEP_NAME))

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce())
            .add(DepTypes.android + DepTypes.test.capitalize() + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `android test impl deps`() {
        val dependency = androidTestImpl(DEP_NAME)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce())
            .add(DepTypes.android + DepTypes.test.capitalize() + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `debug deps`() {
        val dependency = debug(impl(DEP_NAME))

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.debug + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `release deps`() {
        val dependency = release(impl(DEP_NAME))

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.release + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `debug impl deps`() {
        val dependency = debugImpl(DEP_NAME)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.debug + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `release impl deps`() {
        val dependency = releaseImpl(DEP_NAME)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(DepTypes.release + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `flavored deps`() {
        val flavor = "flavor"
        val dependency = flavor(flavor, impl(DEP_NAME))

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(flavor + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `composite flavored deps`() {
        val flavor1 = "flavor1"
        val flavor2 = "flavor2"
        val flavor3 = "flavor3"

        val dependency = flavor(flavor1, flavor(flavor2, flavor(flavor3, impl(DEP_NAME))))

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).add(flavor1 + flavor2.capitalize() + flavor3.capitalize() + DepTypes.impl.capitalize(), DEP_NAME)
    }

    @Test
    fun `platform dep`() {
        val dependency = impl(platform(DEP_NAME))

        val mockDependencyObject = createMockGradleDependency()
        `when`(project.dependencies.platform(DEP_NAME)).thenReturn(mockDependencyObject)

        project.registerDependency(dependency)

        verify(project.dependencies, atLeastOnce()).platform(DEP_NAME)
    }

    @Test
    fun `platform provider dep`() {
        val dependencyProvider = createMockDependencyProvider()
        val wrappedDependency = impl(platform(dependencyProvider))

        `when`(project.dependencies.platform(dependencyProvider)).thenReturn(dependencyProvider)

        project.registerDependency(wrappedDependency)

        verify(project.dependencies, atLeastOnce()).platform(dependencyProvider)
    }



    companion object {
        const val DEP_NAME = "dep"

        fun createMockGradleDependency(): Dependency = object: Dependency {
            override fun getGroup(): String? = "Mock"

            override fun getName(): String = "Mock"

            override fun getVersion(): String? = "Mock"

            override fun contentEquals(dependency: Dependency): Boolean = TODO()

            override fun copy(): Dependency = this

            override fun getReason(): String? = "Mock"

            override fun because(reason: String?) = TODO()
        }

        fun createMockDependencyProvider(): Provider<MinimalExternalModuleDependency> {
            return object : Provider<MinimalExternalModuleDependency> {
                override fun get(): MinimalExternalModuleDependency = mock()

                override fun getOrNull(): MinimalExternalModuleDependency? = mock()

                override fun isPresent(): Boolean = true

                override fun forUseAtConfigurationTime(): Provider<MinimalExternalModuleDependency> {
                    TODO("Not yet implemented")
                }

                override fun <U : Any?, R : Any?> zip(
                    provider: Provider<U>,
                    biFunction: BiFunction<in MinimalExternalModuleDependency, in U, out R>
                ): Provider<R> {
                    TODO("Not yet implemented")
                }

                override fun orElse(provider: Provider<out MinimalExternalModuleDependency>): Provider<MinimalExternalModuleDependency> {
                    TODO("Not yet implemented")
                }

                override fun orElse(value: MinimalExternalModuleDependency): Provider<MinimalExternalModuleDependency> {
                    TODO("Not yet implemented")
                }

                override fun <S : Any?> flatMap(transformer: Transformer<out Provider<out S>, in MinimalExternalModuleDependency>): Provider<S> {
                    TODO("Not yet implemented")
                }

                override fun <S : Any?> map(transformer: Transformer<out S, in MinimalExternalModuleDependency>): Provider<S> {
                    TODO("Not yet implemented")
                }

                override fun getOrElse(defaultValue: MinimalExternalModuleDependency): MinimalExternalModuleDependency {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}