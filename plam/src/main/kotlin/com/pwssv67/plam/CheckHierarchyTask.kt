package com.pwssv67.plam

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.tasks.TaskAction

abstract class CheckHierarchyTask: DefaultTask() {
    init {
        group = "custom"
        description = "Checks that modules, current module is depending on, are of correct type"
    }

    @TaskAction
    fun checkHierarchy() {
        val currentModuleType = project.extensions.extraProperties.get(ModuleType.KEY) as ModuleType
        getDependencyModules()
            .forEach {
                logger.lifecycle("Checking module ${it.name}")
                val moduleType = it.dependencyProject.extensions.extraProperties.get(ModuleType.KEY) as ModuleType

                if (!currentModuleType.canDependOn(moduleType)) {
                    throw RuntimeException("Module ${project.name} with type ${currentModuleType} cannot depend on ${it.name} with type ${moduleType}")
                } else {
                    logger.lifecycle("Everything is great!\nModule ${project.name} with type ${currentModuleType} can depend on ${it.name} with type ${moduleType}")
                }
            }
    }

    private fun getDependencyModules() = project.configurations
        .flatMap { it.allDependencies }
        .filterIsInstance<ProjectDependency>()
        .distinctBy { it.name }
        .filter { it.name != project.name }
}