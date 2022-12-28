package com.dynamic.program.trees_and_graphs.dfs

import com.dynamic.program.trees_and_graphs.TreeNode

/**
 * Minimum Depth of Binary Tree
 *
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 *
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^5].
 * -1000 <= Node.val <= 1000
 */

fun minDepth(root: TreeNode?): Int {
    if (root == null) return 0

    val lCnt = minDepth(root.left)
    val rCnt = minDepth(root.right)
    return if (root.left != null && root.right != null) {
        (Math.min(lCnt, rCnt) + 1)
    } else if (root.left == null) {
        rCnt + 1
    } else {
        lCnt + 1
    }
}

fun main() {
    val head = TreeNode(2, null, TreeNode(3, null, TreeNode(4, null, TreeNode(5, null, TreeNode(6)))))
    println(minDepth(head))
}
