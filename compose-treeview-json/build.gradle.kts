plugins {
    `compose-treeview-multiplatform-lib`
    `compose-treeview-publish`
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidLibrary {
        namespace = "io.github.vooft.compose.treeview.json"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":compose-treeview-core"))
            api(libs.kotlinx.serialization.json)
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
        }
    }
}
