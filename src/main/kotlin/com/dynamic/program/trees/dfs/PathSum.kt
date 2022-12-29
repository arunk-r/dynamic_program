package com.dynamic.program.trees.dfs

import com.dynamic.program.trees.TreeNode

/**
 * Path Sum
 * Given the root of a binary tree and an integer targetSum,
 * return true if there is a path from the root to a leaf such
 * that the sum of the nodes on the path is equal to targetSum,
 * and return false otherwise.
 */

fun pathSum(root: TreeNode, targetSum: Int): Boolean {
    return helperFun(root, 0, targetSum)
}

fun helperFun(node: TreeNode?, sum: Int, targetSum: Int): Boolean {
    if (node == null) return false

    if (node.left == null && node.right == null) {
        return (sum + node.`val`) == targetSum
    }

    val left = helperFun(node.left, sum + node.`val`, targetSum)
    val right = helperFun(node.right, sum + node.`val`, targetSum)

    return left || right
}

fun main() {
    val root = TreeNode(5,
        TreeNode(4, TreeNode(11, TreeNode(7), TreeNode(2))),
        TreeNode(8, TreeNode(13), TreeNode(4, null, TreeNode(1)))
    )
    println(pathSum(root, 22))
}
