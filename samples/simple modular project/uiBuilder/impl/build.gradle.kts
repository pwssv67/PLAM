import com.pwssv67.plam.*

val composeLibs: List<DependencyWrapper> by rootProject.extra

androidImpl(
    listOf(
        impl(project(":nameProvider:api")),
        impl(project(":mylibrary"))
    ) + composeLibs
) {
    namespace += "uiBuilder"
}