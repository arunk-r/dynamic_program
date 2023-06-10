package com.dynamic.program.graphs.dfs.easy

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 *
 * 617. Merge Two Binary Trees
 * Easy
 * 8.2K
 * 279
 * company
 * Apple
 * company
 * Amazon
 * company
 * Facebook
 * You are given two binary trees root1 and root2.
 *
 * Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.
 *
 * Return the merged tree.
 *
 * Note: The merging process must start from the root nodes of both trees.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * Output: [3,4,5,5,4,null,7]
 * Example 2:
 *
 * Input: root1 = [1], root2 = [1,2]
 * Output: [2,2]
 *
 *
 * Constraints:
 *
 * The number of nodes in both trees is in the range [0, 2000].
 * -104 <= Node.val <= 104
 */
class MergeTwoBinaryTrees {
    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (root1 == null && root2 == null) return null
        else if (root2 == null) {
            return root1
        } else if (root1 == null) {
            return root2
        } else {
            val node = TreeNode(root1!!.`val` + root2!!.`val`)
            node.left = mergeTrees(root1!!.left, root2!!.left)
            node.right = mergeTrees(root1!!.right, root2!!.right)
            return node
        }
    }
}
