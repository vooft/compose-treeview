import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("compose-treeview-base")
    id("com.android.kotlin.multiplatform.library")
}

group = "io.github.vooft"
version = System.getenv("TAG") ?: "1.0-SNAPSHOT"

kotlin {
    androidLibrary {
        compileSdk = 35
        minSdk = 21
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    iosArm64()
    iosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

    }
}
