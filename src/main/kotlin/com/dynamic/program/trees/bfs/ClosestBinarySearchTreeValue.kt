package com.dynamic.program.trees.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Closest Binary Search Tree Value
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.
 *
 * Example 1:
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 *
 * Example 2:
 * Input: root = [1], target = 4.428571
 * Output: 1
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^9
 * -10^9 <= target <= 10^9
 */

fun closestValue(root: TreeNode?, target: Double): Int {
    var node: TreeNode? = root
    var value = 0.0
    var closest = node?.`val`?.toDouble() ?: 0.0
    while (node != null) {
        value = node.`val`.toDouble()
        closest = if (Math.abs(value - target) < Math.abs(closest - target)) value else closest
        node = if (target < value) node.left else node.right
    }
    return closest.toInt()
}

fun main() {
    val head = TreeNode(3, TreeNode(2, TreeNode(1)), TreeNode(4))
    println(closestValue(head, 4.14))
}