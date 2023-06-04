package com.pwssv67.plam.ext

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.junit.Test
import org.mockito.Mockito.*

class KotlinOptionsExtKtTest {

    @Test
    fun testKotlinOptionsAreApplied() {
        val extension = mock<LibraryExtension>(
            withSettings()
                .extraInterfaces(ExtensionAware::class.java)
        )

        extension as ExtensionAware

        `when`(extension.extensions).thenReturn(mock())

        extension.kotlinOptions {
            jvmTarget = "1.8"
        }

        verify(extension.extensions, atLeastOnce()).configure(eq("kotlinOptions"), any<Action<KotlinJvmOptions>>())
    }
}