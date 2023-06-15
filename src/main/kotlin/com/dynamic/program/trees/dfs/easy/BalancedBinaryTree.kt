package com.dynamic.program.trees.dfs.easy

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/balanced-binary-tree/description/
 *
 * 110. Balanced Binary Tree
 * Easy
 * 9.1K
 * 513
 * company
 * Amazon
 * company
 * Facebook
 * company
 * Adobe
 * Given a binary tree, determine if it is
 * height-balanced
 * .
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 *
 * Input: root = []
 * Output: true
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -104 <= Node.val <= 104
 */
class BalancedBinaryTree {
    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) return true
        val left = depth(root.left, 1)
        val right = depth(root.right, 1)
        return Math.abs(left-right) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right)
    }

    fun depth(root: TreeNode?, d: Int): Int {
        if(root == null) return d
        val left = depth(root.left, d+1)
        val right = depth(root.right, d+1)
        return maxOf(left, right)
    }
}
