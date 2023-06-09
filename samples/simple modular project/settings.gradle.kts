pluginManagement {
    repositories {
//        mavenLocal {
//            repositories {
//                url = uri("${System.getenv("HOME")}/programming/maven-repository")
//            }
//        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")
include(":mylibrary")
include(":api")
include(":nameProvider:api")
include(":nameProvider:impl")
include(":uiBuilder:impl")
