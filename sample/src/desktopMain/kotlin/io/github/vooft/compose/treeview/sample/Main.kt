package io.github.vooft.compose.treeview.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "sample",
    ) {
        App()
    }
}
