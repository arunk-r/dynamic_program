package com.dynamic.program.trees.dfs.hard

import com.dynamic.program.trees.TreeNode

/**
 * 297. Serialize and Deserialize Binary Tree
 * Hard
 *
 * company
 * Amazon
 * company
 * Microsoft
 * company
 * Uber
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 */
class SerializeAndDeserializeBinaryTree {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        return collect(root)
    }

    private fun collect(root: TreeNode?): String {
        if (root == null) {
            return "null"
        }
        else {
            var s = ""
            s += root.`val`.toString() + ","
            s += collect(root.left) + ","
            s += collect(root.right)
            return s
        }
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val lst = data.split(",").toMutableList()
        return build(lst)
    }
    private fun build(lst: MutableList<String>): TreeNode? {
        return if (lst[0] == "null") {
            lst.removeAt(0)
            null
        } else {
            val v = lst[0].toInt()
            lst.removeAt(0)
            val root = TreeNode(v)
            root.left = build(lst)
            root.right = build(lst)
            root
        }
    }
}
