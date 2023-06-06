# PLAM
[![Maven Central](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/io/github/pwssv67/plam/io.github.pwssv67.plam.gradle.plugin/maven-metadata.xml.svg?colorB=ff6b00&label=Gradle%20Plugin%20Portal)](https://plugins.gradle.org/plugin/io.github.pwssv67.plam)

PLAM - PLugin for Android Modularisation, symplifying architectural tasks and verifying dependencies between different module types.

## Easy to use

If you are just starting a new project, that's all build script code you will need:
```kotlin
//project build.gradle.kts
import com.pwssv67.plam.*

plugins {
    id("com.android.application") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "7.4.0" apply false
    id("io.github.pwssv67.plam") version "0.2.0" apply false
}

configure {
    appConfig {
        namespace = "com.sample.app"
        compileSdk = 33

        defaultConfig {
            applicationId = "com.sample.app"
            minSdk = 26
        }
    }
}

//app module build.gradle.kts
import com.pwssv67.plam.*

androidApp(
    dependencies = listOf( //possible list of dependencies
        impl("androidx.core:core-ktx:1.10.1"),
        impl("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"),
    ) + listOf(
        testImpl("junit:junit:4.13.2")
    )
)
```

## Keeping track of module types
There are currently four module types
* App - for app itself, as build endpoint, accumulating all other dependencies. Can be used only in other **Apps**. Can use anything.
* FeatureAPI - feature API or signature, like starting params needed for feature. Has the same role as an interface to an actual implementation: for dependency inversion. Can use only **libraries**.
* FeatureImpl - feature actual implementation. Can only be used in **App** module. Can use other **feature's APIs** and **libraries**
* Library - widely used code, like network requests, design components, etc. Can be added to any module.

### Typical module structure
```mermaid
flowchart TD
    A{App}
    B([Network Library])
    C([Design Library])
    D(Feature 1 API)
    E(Feature 2 API)
    F[Feature 1 Implementation]
    G[Feature 2 Implementation]

    A --> F & G
    F --> D & B & C & E
    G --> E & C 
```
*For the sake of simplicity, App's dependency to feature API's is omitted.*

## Use Samples
[Sample project](https://github.com/pwssv67/PLAM/samples/project)
