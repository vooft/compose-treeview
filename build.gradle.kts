buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("composeTreeviewPublishToMavenCentral") {
    dependsOn(
        subprojects.mapNotNull { it.tasks.findByName("publishAndReleaseToMavenCentral") } +
                gradle.includedBuilds.map { it.task(":publishAndReleaseToMavenCentral") }
    )
}

tasks.register("composeTreeviewPublishToMavenLocal") {
    dependsOn(
        subprojects.mapNotNull { it.tasks.findByName("publishToMavenLocal") } +
                gradle.includedBuilds.map { it.task(":publishToMavenLocal") }
    )
}
