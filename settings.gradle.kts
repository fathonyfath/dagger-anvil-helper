@file:Suppress("UnstableApiUsage")

include(":multiscope-binding-interface")


include(":multiscope-binding")


include(":feature-counter")


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
        includeBuild("anvil-helper")
    }
}

rootProject.name = "Dagger Anvil Helper"
include(":app")
include(":di-interface")
include(":feature-a-interface")
include(":feature-a")
include(":time-provider-interface")
include(":time-provider")
include(":di-scopes")
include(":feature-b")
include(":feature-b-interface")
include(":viewmodel-factory")
include(":common-scopes")
include(":feature-counter-interface")
