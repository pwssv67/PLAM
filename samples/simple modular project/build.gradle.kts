import com.pwssv67.plam.*
import com.pwssv67.plam.ext.kotlinOptions

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.0.0" apply false
    id("io.github.pwssv67.plam") version "0.2.2" apply false
}

plam {
    appConfig {
        namespace = "com.pwssv67"
        compileSdk = 33

        defaultConfig {
            applicationId = "com.pwssv67.sampleApp"
            minSdk = 26
            targetSdk = 33
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
        }

        kotlinOptions {
            jvmTarget = "1.8"
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    moduleConfig {
        namespace = "com.pwssv67"
        compileSdk = 33

        defaultConfig {
            minSdk = 26

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}

val composeLibs by extra(
    listOf(
        impl(platform("androidx.compose:compose-bom:2023.03.00")),
        impl("androidx.compose.ui:ui"),
        impl("androidx.compose.ui:ui-graphics"),
        impl("androidx.compose.ui:ui-tooling-preview"),
        impl("androidx.compose.material3:material3"),
    ) + listOf(
        androidTestImpl(platform("androidx.compose:compose-bom:2023.03.00")),
        debugImpl("androidx.compose.ui:ui-tooling"),
        debugImpl("androidx.compose.ui:ui-test-manifest")
    )
)