package io.github.vooft.compose.treeview.sample.tree

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.vooft.compose.treeview.core.node.BranchNode
import io.github.vooft.compose.treeview.core.node.Node
import io.github.vooft.compose.treeview.core.tree.Tree

interface TreeScreen<T> : Screen {

    val title: String

    private val Tree<T>.firstBranchNode: Node<T>
        get() = nodes
            .filterIsInstance<BranchNode<T>>()
            .first()

    @Composable
    override fun Content() {
        val tree = composeTree()

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(title)
            TreeContent(
                tree = tree,
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 4.dp)
            )
            TreeController(tree)
        }
    }

    @Composable
    fun composeTree(): Tree<T>

    @Composable
    fun TreeContent(tree: Tree<T>, modifier: Modifier)

    @Composable
    private fun TopBar(title: String) {
        val navigator = LocalNavigator.currentOrThrow

        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(
                    onClick = { navigator.pop() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }

    @Composable
    private fun TreeController(tree: Tree<T>) {
        Column {
            TreeControllerTitle("Expand")
            LazyRow {
                item {
                    TreeControllerButton("Root") { tree.expandRoot() }
                    TreeControllerButton("Node") { tree.expandNode(tree.firstBranchNode) }
                    TreeControllerButton("Depth 2") { tree.expandUntil(2) }
                    TreeControllerButton("All") { tree.expandAll() }
                }
            }

            TreeControllerTitle("Collapse")
            LazyRow {
                item {
                    TreeControllerButton("Root") { tree.collapseRoot() }
                    TreeControllerButton("Node") { tree.collapseNode(tree.firstBranchNode) }
                    TreeControllerButton("Depth 2") { tree.collapseFrom(2) }
                    TreeControllerButton("All") { tree.collapseAll() }
                }
            }

            TreeControllerTitle("Select")
            LazyRow {
                item {
                    TreeControllerButton("Toggle") { tree.toggleSelection(tree.firstBranchNode) }
                    TreeControllerButton("Clear") { tree.clearSelection() }
                }
            }
        }
    }

    @Composable
    private fun TreeControllerTitle(text: String) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }

    @Composable
    private fun TreeControllerButton(text: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Text(text)
        }
    }
}
