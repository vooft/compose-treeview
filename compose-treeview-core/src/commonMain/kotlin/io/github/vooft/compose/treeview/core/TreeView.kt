package io.github.vooft.compose.treeview.core

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.vooft.compose.treeview.core.node.Node
import io.github.vooft.compose.treeview.core.tree.Tree
import io.github.vooft.compose.treeview.core.tree.extension.ExpandableTree
import io.github.vooft.compose.treeview.core.tree.extension.SelectableTree

typealias OnNodeClick<T> = ((Node<T>) -> Unit)?
typealias NodeIcon<T> = @Composable (Node<T>) -> Painter?

@ConsistentCopyVisibility
@Immutable
data class TreeViewScope<T> internal constructor(
    internal val expandableManager: ExpandableTree<T>,
    internal val selectableManager: SelectableTree<T>,
    internal val style: TreeViewStyle<T>,
    internal val onClick: OnNodeClick<T>,
    internal val onLongClick: OnNodeClick<T>,
    internal val onDoubleClick: OnNodeClick<T>,
)

data class TreeViewStyle<T>(
    val toggleIcon: NodeIcon<T> = { rememberVectorPainter(Icons.Default.ChevronRight) },
    val toggleIconSize: Dp = 16.dp,
    val toggleIconColorFilter: ColorFilter? = null,
    val toggleShape: Shape = CircleShape,
    val toggleIconRotationDegrees: Float = 90f,
    val nodeIconSize: Dp = 24.dp,
    val nodePadding: PaddingValues = PaddingValues(all = 4.dp),
    val nodeShape: Shape = RoundedCornerShape(size = 4.dp),
    val nodeSelectedBackgroundColor: Color = Color.LightGray.copy(alpha = .8f),
    val nodeCollapsedIcon: NodeIcon<T> = { null },
    val nodeCollapsedIconColorFilter: ColorFilter? = null,
    val nodeExpandedIcon: NodeIcon<T> = nodeCollapsedIcon,
    val nodeExpandedIconColorFilter: ColorFilter? = nodeCollapsedIconColorFilter,
    val nodeNameStartPadding: Dp = 0.dp,
    val nodeNameTextStyle: TextStyle = DefaultNodeTextStyle,
    val useHorizontalScroll: Boolean = true
) {

    companion object {
        val DefaultNodeTextStyle: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    }
}

@Composable
fun <T> TreeView(
    tree: Tree<T>,
    modifier: Modifier = Modifier,
    onClick: OnNodeClick<T> = tree::onNodeClick,
    onDoubleClick: OnNodeClick<T> = tree::onNodeClick,
    onLongClick: OnNodeClick<T> = tree::toggleSelection,
    style: TreeViewStyle<T> = TreeViewStyle(),
    listState: LazyListState = rememberLazyListState(),
) {
    val scope = remember(tree) {
        TreeViewScope(
            expandableManager = tree,
            selectableManager = tree,
            style = style,
            onClick = onClick,
            onLongClick = onLongClick,
            onDoubleClick = onDoubleClick,
        )
    }

    with(scope) {
        LazyColumn(
            state = listState,
            modifier = modifier
                .fillMaxWidth()
                .run {
                    if (style.useHorizontalScroll) {
                        horizontalScroll(rememberScrollState())
                    } else {
                        this
                    }
                }
        ) {
            items(tree.nodes, { it.key }) { node ->
                Node(node)
            }
        }
    }
}

private fun <T> Tree<T>.onNodeClick(node: Node<T>) {
    clearSelection()
    toggleExpansion(node)
}
