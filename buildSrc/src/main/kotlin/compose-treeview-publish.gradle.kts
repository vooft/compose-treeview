plugins {
    id("org.jetbrains.dokka")
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    pom {
        name = "compose-treeview"
        description = "Compose Multiplatform TreeView implementation"
        url = "https://github.com/vooft/compose-treeview"
        licenses {
            license {
                name = "The MIT License"
                url = "https://opensource.org/licenses/MIT"
            }
        }
        scm {
            connection = "https://github.com/vooft/compose-treeview"
            url = "https://github.com/vooft/compose-treeview"
        }
        developers {
            developer {
                name = "compose-treeview team"
            }
        }
    }
}
