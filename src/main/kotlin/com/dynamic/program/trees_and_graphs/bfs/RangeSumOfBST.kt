package com.dynamic.program.trees_and_graphs.bfs

import com.dynamic.program.trees_and_graphs.TreeNode

/**
 * Range Sum of BST
 * Given the root node of a binary search tree and two integers low and high,
 * return the sum of values of all nodes with a value in the inclusive range [low, high].
 */

fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    if (root == null) return 0
    var ans = 0
    if (root.`val` in low..high) {
        ans = root.`val`
    }
    if (root.`val` > low) {
        ans += rangeSumBST(root.left, low, high)
    }
    if (root.`val` < high) {
        ans += rangeSumBST(root.right, low, high)
    }
    return ans
}

fun main() {
    val head = TreeNode(10,
    TreeNode(5, TreeNode(3), TreeNode(7)),
            TreeNode(15, null, TreeNode(18))
    )

    println(rangeSumBST(head, 7, 15))
}