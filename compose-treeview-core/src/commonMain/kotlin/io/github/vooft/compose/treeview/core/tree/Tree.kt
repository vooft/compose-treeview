package io.github.vooft.compose.treeview.core.tree

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import io.github.vooft.compose.treeview.core.node.Node
import io.github.vooft.compose.treeview.core.node.TreeApplier
import io.github.vooft.compose.treeview.core.tree.extension.ExpandableTree
import io.github.vooft.compose.treeview.core.tree.extension.ExpandableTreeHandler
import io.github.vooft.compose.treeview.core.tree.extension.SelectableTree
import io.github.vooft.compose.treeview.core.tree.extension.SelectableTreeHandler

@DslMarker
private annotation class TreeMarker

@ConsistentCopyVisibility
@Immutable
@TreeMarker
data class TreeScope internal constructor(
    val depth: Int,
    internal val isExpanded: Boolean = false,
    internal val expandMaxDepth: Int = 0
)

@Stable
class Tree<T> internal constructor(
    val nodes: List<Node<T>>
) : ExpandableTree<T> by ExpandableTreeHandler(nodes),
    SelectableTree<T> by SelectableTreeHandler(nodes)

@Composable
fun <T> Tree(content: @Composable TreeScope.() -> Unit): Tree<T> {
    val applier = remember { TreeApplier<T>() }
    val compositionContext = rememberCompositionContext()
    val composition = remember(applier, compositionContext) { Composition(applier, compositionContext) }
    composition.setContent { TreeScope(depth = 0).content() }
    return remember(applier) { Tree(applier.children) }
}
