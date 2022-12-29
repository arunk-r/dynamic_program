package com.dynamic.program.trees.dfs

import com.dynamic.program.trees.TreeNode
import kotlin.math.max

/**
 * Count Good Nodes in Binary Tree
 * Given the root of a binary tree, find the number of nodes that are good.
 * A node is good if the path between the root and the node has no nodes with a greater value.
 */

fun goodNodes(root: TreeNode): Int {
    if (root.`val` > Int.MIN_VALUE) {
        val lCnt = goodNodes(root.left, root.`val`)
        val rCnt = goodNodes(root.right, root.`val`)
        return lCnt + rCnt + 1
    }
    return -1
}

fun goodNodes(node: TreeNode?, maxSoFar: Int): Int {
    if (node == null) return 0
    return if (node.`val` > maxSoFar) {
        val lCnt = goodNodes(node.left, max(maxSoFar, node.`val`))
        val rCnt = goodNodes(node.right, max(maxSoFar, node.`val`))
        lCnt + rCnt + 1
    } else {
        val lCnt = goodNodes(node.left, maxSoFar)
        val rCnt = goodNodes(node.right, maxSoFar)
        lCnt + rCnt
    }
}

fun main() {
    val root = TreeNode(5,
        TreeNode(4, TreeNode(11, TreeNode(7), TreeNode(2))),
        TreeNode(8, TreeNode(13), TreeNode(4, null, TreeNode(1)))
    )
    println(goodNodes(root))
}
