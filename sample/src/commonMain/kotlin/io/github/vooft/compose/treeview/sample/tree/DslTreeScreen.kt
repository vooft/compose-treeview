package io.github.vooft.compose.treeview.sample.tree

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vooft.compose.treeview.core.TreeView
import io.github.vooft.compose.treeview.core.TreeViewStyle
import io.github.vooft.compose.treeview.core.node.Branch
import io.github.vooft.compose.treeview.core.node.Leaf
import io.github.vooft.compose.treeview.core.tree.Tree

object DslTreeScreen : TreeScreen<String> {

    override val title = "DSL Tree"

    @Composable
    override fun composeTree(): Tree<String> = Tree {
        Branch("Animalia") {
            Branch("Chordata") {
                Branch("Mammalia") {
                    Branch("Carnivora") {
                        Branch("Canidae") {
                            Branch("Canis") {
                                Leaf("Wolf", customIcon = { EmojiIcon("\uD83D\uDC3A") })
                                Leaf("Dog", customIcon = { EmojiIcon("\uD83D\uDC36") })
                            }
                        }
                        Branch("Felidae") {
                            Branch("Felis") {
                                Leaf("Cat", customIcon = { EmojiIcon("\uD83D\uDC31") })
                            }
                            Branch("Panthera") {
                                Leaf("Lion", customIcon = { EmojiIcon("\uD83E\uDD81") })
                            }
                        }
                    }
                }
            }
        }
        Branch("Plantae") {
            Branch("Solanales") {
                Branch("Convolvulaceae") {
                    Branch("Ipomoea") {
                        Leaf("Sweet Potato", customIcon = { EmojiIcon("\uD83C\uDF60") })
                    }
                }
                Branch("Solanaceae") {
                    Leaf("Potato", customIcon = { EmojiIcon("\uD83E\uDD54") })
                    Leaf("Tomato", customIcon = { EmojiIcon("\uD83C\uDF45") })
                }
            }
        }
    }

    @Composable
    override fun TreeContent(tree: Tree<String>, modifier: Modifier) {
        TreeView(
            tree = tree,
            style = TreeViewStyle(
                nodeNameStartPadding = 4.dp
            ),
            modifier = modifier
        )
    }

    @Composable
    private fun EmojiIcon(emoji: String) {
        Text(emoji)
    }
}
