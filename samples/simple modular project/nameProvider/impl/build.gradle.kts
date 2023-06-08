import com.pwssv67.plam.*

androidImpl(
    dependencyList = listOf(
        impl(project(":nameProvider:api"))
    )
) {
    namespace += "nameProvider.impl"
}