package io.github.vooft.compose.treeview.core.node

import androidx.compose.runtime.Composable
import io.github.vooft.compose.treeview.core.TreeViewScope
import io.github.vooft.compose.treeview.core.node.extension.ExpandableNode
import io.github.vooft.compose.treeview.core.node.extension.ExpandableNodeHandler
import io.github.vooft.compose.treeview.core.node.extension.SelectableNode
import io.github.vooft.compose.treeview.core.node.extension.SelectableNodeHandler
import io.github.vooft.ktuuid.UUID

typealias NodeComponent<T> = @Composable TreeViewScope<T>.(Node<T>) -> Unit

sealed interface Node<T> {

    val key: String

    val content: T

    val name: String

    val depth: Int

    val isSelected: Boolean

    val iconComponent: NodeComponent<T>

    val nameComponent: NodeComponent<T>
}

class LeafNode<T> internal constructor(
    override val content: T,
    override val depth: Int,
    override val key: String = UUID.randomUUID().toString(),
    override val name: String = content.toString(),
    override val iconComponent: NodeComponent<T> = { DefaultNodeIcon(it) },
    override val nameComponent: NodeComponent<T> = { DefaultNodeName(it) }
) : Node<T>,
    SelectableNode by SelectableNodeHandler()

class BranchNode<T> internal constructor(
    override val content: T,
    override val depth: Int,
    override val key: String = UUID.randomUUID().toString(),
    override val name: String = content.toString(),
    override val iconComponent: NodeComponent<T> = { DefaultNodeIcon(it) },
    override val nameComponent: NodeComponent<T> = { DefaultNodeName(it) }
) : Node<T>,
    SelectableNode by SelectableNodeHandler(),
    ExpandableNode by ExpandableNodeHandler()
