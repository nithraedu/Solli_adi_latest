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
        jcenter()
        maven { url=uri("https://jitpack.io")
        }
        maven {
            url = uri("https://maven.google.com")
        }
        maven {
            url = uri("https://android-sdk.is.com/")
        }
    }
}

rootProject.name = "SolliAdi_New"
include(":app")
 