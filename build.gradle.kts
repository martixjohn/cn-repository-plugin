plugins {
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "2.1.1"
}

group = "io.github.martixjohn"
version = "1.0.0"
description = "Add China repositories to your gradle project"


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
    withJavadocJar()
}


dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    website = "https://github.com/martixjohn/cn-repository-plugin"
    vcsUrl = "https://github.com/martixjohn/cn-repository-plugin.git"

    plugins {
        create("settings-plugin") {
            id = "${project.group}.cn-repository-plugin"
            implementationClass = "io.github.martixjohn.cnrepository.CnRepositorySettingsPlugin"
            displayName = "A plugin to add China repositories (Settings Scope)"
            description =
                "Add China repositories in Settings scope, modifying the dependencyResolutionManagement and the pluginManagement DSL"
            tags = listOf("settings-plugin", "china-plugin", "repository")
        }

        create("project-plugin") {
            id = "${project.group}.cn-repository-project-plugin"
            implementationClass = "io.github.martixjohn.cnrepository.CnRepositoryProjectPlugin"
            displayName = "A plugin to add China repositories (Project Scope)"
            description = "Add China repositories in Project scope, modifying the repositories DSL"
            tags = listOf("project-plugin", "china-plugin", "repository")
        }
    }
}