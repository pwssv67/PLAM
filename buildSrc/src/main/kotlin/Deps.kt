object Deps {
    val androidPluginApi = "com.android.tools.build:gradle-api:${Versions.androidPluginApi}"
    val kotlinGradlePluginApi = "org.jetbrains.kotlin:kotlin-gradle-plugin-api:${Versions.kotlinPluginApi}"

    object test {
        val junit = "junit:junit:${Versions.junit}"
        val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    }
}