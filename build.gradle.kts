plugins {
    `java-gradle-plugin`
    `maven-publish`
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
    plugins {
        create("settings-plugin") {
            id = "${project.group}.cn-repository-plugin"
            implementationClass = "io.github.martixjohn.cnrepository.CnRepositorySettingsPlugin"
            displayName = "CN repository plugin at Settings scope"
            description = project.description
        }

        create("project-plugin") {
            id = "${project.group}.cn-repository-project-plugin"
            implementationClass = "io.github.martixjohn.cnrepository.CnRepositoryProjectPlugin"
            displayName = "CN Repository Plugin at Project scope"
            description = project.description
        }
    }
}





publishing {
    publications {
        create<MavenPublication>("myLibrary") {
            from(components["java"])

        }
    }
    repositories {
        mavenLocal()
    }
}

