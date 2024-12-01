package io.github.vooft.compose.treeview.sample.tree

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.vooft.compose.treeview.core.TreeView
import io.github.vooft.compose.treeview.core.tree.Tree
import io.github.vooft.compose.treeview.json.JsonTree
import io.github.vooft.compose.treeview.json.JsonTreeViewStyle
import kotlinx.serialization.json.JsonElement

object JsonTreeScreen : TreeScreen<JsonElement> {

    override val title = "JSON Tree"

    @Composable
    override fun composeTree(): Tree<JsonElement> {
        return JsonTree(responseJson)
    }

    @Composable
    override fun TreeContent(tree: Tree<JsonElement>, modifier: Modifier) {
        TreeView(
            tree = tree,
            style = JsonTreeViewStyle(),
            modifier = modifier
        )
    }
}
