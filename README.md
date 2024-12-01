![Build and test](https://github.com/vooft/compose-treeview/actions/workflows/build.yml/badge.svg?branch=main)
![Releases](https://img.shields.io/github/v/release/vooft/compose-treeview)
![Maven Central](https://img.shields.io/maven-central/v/io.github.vooft/compose-treeview-core)
![License](https://img.shields.io/github/license/vooft/compose-treeview)

![badge-platform-jvm]
![badge-platform-wasm]
![badge-platform-android]
![badge-platform-ios]

# compose-treeview

A tree view for Compose Multiplatform. This project is a fork of [bonsai](https://github.com/adrielcafe/bonsai) by Adriel Cafe.

# Supported platforms

* Android
* iOS
* Desktop
* Web (wasm) ([demo](https://vooft.github.io/compose-treeview/))

# Quick start

Add the dependency to your project:

```kotlin
kotlin {
    ...

    sourceSets {
        commonMain.dependencies {
            implementation("io.github.vooft:compose-treeview-core:<version>")
        }
    }
}
```

Create a tree using DSL:

```kotlin
@Composable
fun TreeViewExample() {
    // build tree structure
    val tree = Tree {
        Branch("Mammalia") {
            Branch("Carnivora") {
                Branch("Canidae") {
                    Branch("Canis") {
                        Leaf("Wolf", customIcon = { EmojiIcon("🐺") })
                        Leaf("Dog", customIcon = { EmojiIcon("🐶") })
                    }
                }
                Branch("Felidae") {
                    Branch("Felis") {
                        Leaf("Cat", customIcon = { EmojiIcon("🐱") })
                    }
                    Branch("Panthera") {
                        Leaf("Lion", customIcon = { EmojiIcon("🦁") })
                    }
                }
            }
        }
    }

    // render the tree
    TreeView(tree)
}
```
