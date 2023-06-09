plugins {
    `kotlin-dsl`
    //publishing plugin
    `maven-publish`
    id("com.gradle.plugin-publish") version "1.2.0"
    kotlin("jvm") version "1.7.10"
}

group = "io.github.pwssv67.plam"

repositories {
    mavenCentral()
    google()
}

val gradle7 = sourceSets.create("gradle7")
val gradle8 = sourceSets.create("gradle8")

java {
    registerFeature(gradle7.name) {
        usingSourceSet(sourceSets["main"])
        capability(project.group.toString(), project.name, project.version.toString())
    }

    registerFeature(gradle8.name) {
        usingSourceSet(sourceSets["main"])
        capability(project.group.toString(), project.name, project.version.toString())
        this.withSourcesJar()
    }

    //specify java version
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

configurations.configureEach {
    if (isCanBeConsumed && name.startsWith(gradle7.name))  {
        attributes {
            attribute(GradlePluginApiVersion.GRADLE_PLUGIN_API_VERSION_ATTRIBUTE,
                objects.named("7.0"))
        }
    }
    if (isCanBeConsumed && name.startsWith(gradle8.name))  {
        attributes {
            attribute(GradlePluginApiVersion.GRADLE_PLUGIN_API_VERSION_ATTRIBUTE,
                objects.named("8.0"))
        }
    }
}
tasks.named<Copy>(gradle7.processResourcesTaskName) {
    val copyPluginDescriptors = rootSpec.addChild()
    copyPluginDescriptors.into("META-INF/gradle-plugins")
    copyPluginDescriptors.from(tasks.pluginDescriptors)
}

tasks.named<Copy>(gradle8.processResourcesTaskName) {
    val copyPluginDescriptors = rootSpec.addChild()
    copyPluginDescriptors.into("META-INF/gradle-plugins")
    copyPluginDescriptors.from(tasks.pluginDescriptors)
}

val integrationTest by sourceSets.creating

dependencies {
    implementation(Deps.androidPluginApi)
    implementation(kotlin("stdlib"))
    implementation(Deps.kotlinGradlePluginApi)
    "gradle7Implementation"(gradleApi())
    "gradle8Implementation"(gradleApi())
    testImplementation(Deps.test.junit)
    testImplementation(Deps.test.mockito)


}

gradlePlugin {
    website.set("https://github.com/pwssv67/PLAM.git")
    vcsUrl.set("https://github.com/pwssv67/PLAM.git")
    plugins {
        create("PLAM") {
            id = "io.github.pwssv67.plam"
            implementationClass = "com.pwssv67.plam.PlamPlugin"
            version = "0.3.0"

            displayName = "PLugin for Android Modularisation"
            description = "A plugin that helps you modularise projects and verify dependencies between different types of modules"
            tags.set(listOf("modularisation", "architecture", "dependencies"))
        }
    }
}


publishing {
    repositories {
//        mavenLocal {
//            url = uri("${System.getenv("HOME")}/programming/maven-repository")
//        }
//        mavenCentral()
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

