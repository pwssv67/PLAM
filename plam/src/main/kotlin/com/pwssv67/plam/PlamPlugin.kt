package com.pwssv67.plam

import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class PlamPlugin : Plugin<Project> {
    override fun apply(project: Project) {
            val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
            androidComponents.onVariants { variant ->
                val task = project.tasks.register(variant.name + "CheckHierarchy", CheckHierarchyTask::class.java)

                project.tasks.whenTaskAdded {
                    if (name.contains(variant.name, ignoreCase = true)) {
                        dependsOn(task)
                    }
                }
            }
    }
}

