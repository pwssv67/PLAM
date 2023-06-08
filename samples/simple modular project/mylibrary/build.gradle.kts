import com.pwssv67.plam.DependencyWrapper
import com.pwssv67.plam.androidLibrary
import com.pwssv67.plam.androidTestImpl
import com.pwssv67.plam.impl
import com.pwssv67.plam.testImpl

val composeLibs: List<DependencyWrapper> by rootProject.extra

androidLibrary(
    dependencyList = listOf(
        impl("androidx.core:core-ktx:1.9.0"),
        impl("androidx.appcompat:appcompat:1.6.1"),
        impl("com.google.android.material:material:1.8.0")
    ) + listOf(
        testImpl("junit:junit:4.13.2"),
        androidTestImpl("androidx.test.ext:junit:1.1.5"),
        androidTestImpl("androidx.test.espresso:espresso-core:3.5.1")
    ) + composeLibs
) {
    namespace += ".mylibrary"
}