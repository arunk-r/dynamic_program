package com.dynamic.program.trees.dfs

import com.dynamic.program.trees.TreeNode

/**
 * Diameter of Binary Tree
 *
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -100 <= Node.val <= 100
 */

var maxD = 0
fun diameterOfBinaryTree(root: TreeNode?): Int {
    if (root == null) return 0
    maxD = 0
    val left = helper(root)
    return maxD
}

fun helper(node: TreeNode?): Int {
    if (node == null) return 0
    val left = helper(node.left)
    val right = helper(node.right)
    maxD = Math.max(maxD, (left + right))
    return Math.max(left, right) + 1
}