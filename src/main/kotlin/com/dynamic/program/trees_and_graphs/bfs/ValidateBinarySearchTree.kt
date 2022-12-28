package com.dynamic.program.trees_and_graphs.bfs

import com.dynamic.program.trees_and_graphs.TreeNode

/**
 * Validate Binary Search Tree
 * Given the root of a binary tree, determine if it is a valid BST.
 */

fun validateBST(root: TreeNode): Boolean {
    return validateBST(root, Int.MIN_VALUE, Int.MAX_VALUE)
}

fun validateBST(node: TreeNode?, min: Int, max: Int): Boolean {
    if (node == null) return true
    if (node.`val` !in min .. max) {
        return false
    }
    val left = validateBST(node.left, min, node.`val`)
    val right = validateBST(node.right, node.`val`, max)
    return left && right
}

fun main() {
    val head = TreeNode(10,
            TreeNode(5, TreeNode(2), TreeNode(8)),
            TreeNode(12, null, TreeNode(23))
    )
    println(validateBST(head))
}