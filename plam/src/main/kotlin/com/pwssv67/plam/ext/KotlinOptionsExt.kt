@file:Suppress("unused")

package com.pwssv67.plam.ext

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

//TODO find out if it's possible to merge these two into one, with receiver of CommonExtension.
//But the problem as of 10.07.23 is that in some version of Gradle (probably 7 to 8) generic parameter was added, leading to runtime error with ClassNotFound

/**
 * Extension for configuring kotlin version and alike params. Generally this is the copy of [org.gradle.kotlin.dsl.kotlinOptions]
 */
inline fun LibraryExtension.kotlinOptions(crossinline body: KotlinJvmOptions.() -> Unit) {
    val action: Action<KotlinJvmOptions> = Action {
        body.invoke(this)
    }
    (this as ExtensionAware).extensions.configure("kotlinOptions", action)
}

/**
 * Extension for configuring kotlin version and alike params. Generally this is the copy of [org.gradle.kotlin.dsl.kotlinOptions]
 */
inline fun ApplicationExtension.kotlinOptions(crossinline body: KotlinJvmOptions.() -> Unit) {
    val action: Action<KotlinJvmOptions> = Action {
        body.invoke(this)
    }
    (this as ExtensionAware).extensions.configure("kotlinOptions", action)
}