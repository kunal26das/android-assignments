pluginManagement {
    repositories {
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
rootProject.name = "Assignment"
include(":app")
include(":common")
include(":database")
include(":dependency")
include(":feature")
include(":feature:data")
include(":feature:domain")
