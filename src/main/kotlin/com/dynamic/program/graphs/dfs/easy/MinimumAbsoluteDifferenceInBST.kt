package com.dynamic.program.graphs.dfs.easy

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 * 530. Minimum Absolute Difference in BST
 * Easy
 * 2.9K
 * 152
 * company
 * Amazon
 * company
 * Google
 * company
 * Facebook
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 104].
 * 0 <= Node.val <= 105
 *
 *
 * Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
class MinimumAbsoluteDifferenceInBST {
    var diff = Int.MAX_VALUE
    fun getMinimumDifference(root: TreeNode?): Int {
        dfs(root, mutableListOf())
        return diff
    }

    fun dfs(node: TreeNode?, lst: MutableList<Int>) {
        if (node != null) {
            dfs(node.left, lst)
            lst.add(node.`val`)
            if (lst.size >= 2) {
                diff = minOf(diff, Math.abs(lst[lst.size - 2] - lst[lst.size - 1]))
            }
            dfs(node.right, lst)
        }
    }
}
