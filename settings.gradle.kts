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

rootProject.name = "Android Assignments"
include(":app")
include(":epifi")
include(":kutumb")
include(":navi")
include(":radius")
include(":kisan-network")
include(":common")
include(":radius:data")
include(":radius:domain")
include(":dependency")
include(":cred")
include(":cred:stack")
