import com.pwssv67.plam.DependencyWrapper
import com.pwssv67.plam.androidApp
import com.pwssv67.plam.androidTestImpl
import com.pwssv67.plam.impl
import com.pwssv67.plam.testImpl

val composeLibs: List<DependencyWrapper> by rootProject.extra

androidApp(
    dependencyList = listOf(
        impl("androidx.core:core-ktx:1.10.1"),
        impl("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"),
        impl("androidx.activity:activity-compose:1.7.1"),
    ) + listOf(
        testImpl("junit:junit:4.13.2"),
        androidTestImpl("androidx.test.ext:junit:1.1.5"),
        androidTestImpl("androidx.test.espresso:espresso-core:3.5.1"),
        androidTestImpl("androidx.compose.ui:ui-test-junit4"),
    ) + listOf(
        impl(project(":mylibrary")),
        impl(project(":nameProvider:api")),
        impl(project(":nameProvider:impl")),
        impl(project(":uiBuilder:impl"))
    ) + composeLibs
) {
    namespace += ".app"
}