@file:Suppress("unused")

package com.pwssv67.plam.ext

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Action
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Extension for configuring kotlin version and alike params. Generally this is the copy of [org.gradle.kotlin.dsl.kotlinOptions]
 */
inline fun CommonExtension<*,*,*,*,*>.kotlinOptions(crossinline body: KotlinJvmOptions.() -> Unit) {
    val action: Action<KotlinJvmOptions> = Action {
        body.invoke(this)
    }
    (this as ExtensionAware).extensions.configure("kotlinOptions", action)
}