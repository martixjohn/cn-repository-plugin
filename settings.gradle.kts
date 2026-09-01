import java.net.URI

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "cn-repository-plugin"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral {
            this.url = URI("https://maven.aliyun.com/repository/public/")
        }
        gradlePluginPortal()
    }
}


