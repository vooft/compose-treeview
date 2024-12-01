package io.github.vooft.compose.treeview.json

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import io.github.vooft.compose.treeview.core.TreeViewStyle
import io.github.vooft.compose.treeview.core.node.Branch
import io.github.vooft.compose.treeview.core.node.Leaf
import io.github.vooft.compose.treeview.core.tree.Tree
import io.github.vooft.compose.treeview.core.tree.TreeScope
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

fun JsonTreeViewStyle(): TreeViewStyle<JsonElement> = TreeViewStyle(
    nodeNameTextStyle = TreeViewStyle.DefaultNodeTextStyle.copy(
        fontFamily = FontFamily.Monospace
    )
)

@Composable
fun JsonTree(json: String): Tree<JsonElement> = Tree {
    JsonNode(
        key = null,
        jsonElement = Json.parseToJsonElement(json)
    )
}

@Composable
private fun TreeScope.JsonNode(key: String?, jsonElement: JsonElement) {
    when (jsonElement) {
        is JsonNull -> JsonPrimitiveNode(key, jsonElement)
        is JsonPrimitive -> JsonPrimitiveNode(key, jsonElement)
        is JsonObject -> JsonObjectNode(key, jsonElement)
        is JsonArray -> JsonArrayNode(key, jsonElement)
    }
}

@Composable
private fun TreeScope.JsonPrimitiveNode(key: String?, jsonPrimitive: JsonPrimitive) {
    Leaf(
        content = jsonPrimitive,
        name = "${getFormattedKey(key)}${getFormattedValue(jsonPrimitive)}",
    )
}

@Composable
private fun TreeScope.JsonObjectNode(key: String?, jsonObject: JsonObject) {
    Branch(
        content = jsonObject,
        name = "${getFormattedKey(key)}{object}"
    ) {
        jsonObject.entries.forEach { (key, jsonElement) ->
            JsonNode(key, jsonElement)
        }
    }
}

@Composable
private fun TreeScope.JsonArrayNode(key: String?, jsonArray: JsonArray) {
    Branch(
        content = jsonArray,
        name = "${getFormattedKey(key)}[array]"
    ) {
        jsonArray.forEachIndexed { index, jsonElement ->
            JsonNode(index.toString(), jsonElement)
        }
    }
}

private fun getFormattedKey(key: String?) = if (key.isNullOrBlank()) {
    ""
} else {
    "$key: "
}

private fun getFormattedValue(jsonPrimitive: JsonPrimitive) = if (jsonPrimitive.isString) {
    "\"${jsonPrimitive.contentOrNull}\""
} else {
    jsonPrimitive.contentOrNull
}
