package com.dynamic.program.trees.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Minimum Absolute Difference in BST
 * Given the root of a BST, return the minimum absolute difference between the values of any two different nodes in the tree.
 */

fun getMinimumDifference(root: TreeNode): Int {
    var ans = Int.MAX_VALUE
    val data = helperDataList(root)
    for (i in 1 until data.size) {
        ans = Math.min(ans, (data[i] - data[i - 1]))
    }
    return ans
}

fun helperDataList(node: TreeNode?): MutableList<Int> {
    if (node == null) return mutableListOf()
    val left = helperDataList(node.left)
    val right = helperDataList(node.right)
    left.add(node.`val`)
    left.addAll(right)
    return left
}

fun main() {
    val head = TreeNode(9,
            TreeNode(5, TreeNode(1), TreeNode(7)),
            TreeNode(15)
    )
    println(getMinimumDifference(head))
}