plugins {
    `compose-treeview-multiplatform-lib`
    `compose-treeview-publish`
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidLibrary {
        namespace = "io.github.vooft.compose.treeview.core"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.material.icons.extended)
        }
    }
}
