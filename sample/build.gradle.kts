import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    `compose-treeview-base`
    id("com.android.kotlin.multiplatform.library")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidLibrary {
        namespace = "io.github.vooft.compose.treeview.sample.shared"
        compileSdk = 36
        minSdk = 23
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static(rootDirPath)
                    static(projectDirPath)
                }
            }
        }
        binaries.executable()
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = "SampleApp"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(project(":compose-treeview-core"))
            implementation(project(":compose-treeview-json"))

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.compose.voyager)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

compose.desktop {
    application {
        mainClass = "io.github.vooft.compose.treeview.sample.MainKt"
    }
}
