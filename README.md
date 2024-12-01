![Build and test](https://github.com/vooft/compose-treeview/actions/workflows/build.yml/badge.svg?branch=main)
![Releases](https://img.shields.io/github/v/release/vooft/compose-treeview)
![Maven Central](https://img.shields.io/maven-central/v/io.github.vooft/compose-treeview-core)
![License](https://img.shields.io/github/license/vooft/compose-treeview)

![badge-platform-jvm]
![badge-platform-wasm]
![badge-platform-android]
![badge-platform-ios]

[badge-demo] [WASM demo]((https://vooft.github.io/compose-treeview/))

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

<!-- TAG_PLATFORMS -->
[badge-platform-android]: http://img.shields.io/badge/-android-6EDB8D.svg?style=flat
[badge-platform-jvm]: http://img.shields.io/badge/-jvm-DB413D.svg?style=flat
[badge-platform-ios]: http://img.shields.io/badge/-ios-CDCDCD.svg?style=flat
[badge-platform-wasm]: https://img.shields.io/badge/-wasm-624FE8.svg?style=flat
[badge-demo]: https://img.shields.io/badge/-demo-4E8EF7.svg?style=flat
