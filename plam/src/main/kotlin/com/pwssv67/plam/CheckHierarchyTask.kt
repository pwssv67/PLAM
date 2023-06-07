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
                logger.info("Checking module ${it.dependencyProject.displayName}")
                val moduleType = it.dependencyProject.extensions.extraProperties.get(ModuleType.KEY) as ModuleType

                if (!currentModuleType.canDependOn(moduleType)) {
                    throw RuntimeException("Module ${project.displayName} with type ${currentModuleType::class.simpleName} cannot depend on ${it.dependencyProject.displayName} with type ${moduleType::class.simpleName}")
                } else {
                    logger.info("Everything is great!\nModule ${project.displayName} with type ${currentModuleType::class.simpleName} can depend on ${it.dependencyProject.displayName} with type ${moduleType::class.simpleName}")
                }
            }
    }

    private fun getDependencyModules() = project.configurations
        .flatMap { it.allDependencies }
        .filterIsInstance<ProjectDependency>()
        .distinctBy { it.name }
        .filter { it.name != project.name }
}