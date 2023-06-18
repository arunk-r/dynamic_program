package com.dynamic.program.trees.dfs.easy

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 *
 * 543. Diameter of Binary Tree
 * Easy
 * 11.6K
 * 721
 * company
 * Amazon
 * company
 * Facebook
 * company
 * Bloomberg
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 */
class DiameterOfBinaryTree {
    private var diameter = 0
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        helper(root)
        return diameter
    }

    fun helper(root: TreeNode?): Int {
        if (root == null) return 0
        val left = helper(root.left)
        val right = helper(root.right)
        diameter = maxOf(diameter, left+right)
        return 1 + maxOf(left, right)
    }
}
