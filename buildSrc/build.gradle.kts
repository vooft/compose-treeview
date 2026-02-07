plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies {
    // kotlin
    implementation(libs.gradle.plugin.kotlin)

    // android
    implementation(libs.gradle.plugin.android)

    // detekt
    implementation(libs.gradle.plugin.detekt)

    // ktlint
    implementation(libs.gradle.plugin.ktlint)

    // publishing
    implementation(libs.gradle.plugin.dokka)
    implementation(libs.gradle.plugin.maven.central.publish)
}
