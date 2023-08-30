package com.dynamic.program.top.hundred.medium

/**
 *https://leetcode.com/problems/count-univalue-subtrees/
 *
 * 250. Count Univalue Subtrees
 * Medium
 * 1.2K
 * 392
 * company
 * Google
 * company
 * Microsoft
 * company
 * Splunk
 * Given the root of a binary tree, return the number of uni-value
 * subtrees
 * .
 *
 * A uni-value subtree means all nodes of the subtree have the same value.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,1,5,5,5,null,5]
 * Output: 4
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [5,5,5,5,5,null,5]
 * Output: 6
 *
 *
 * Constraints:
 *
 * The number of the node in the tree will be in the range [0, 1000].
 * -1000 <= Node.val <= 1000
 */
class CountUnivalueSubtrees {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun countUnivalSubtrees(root: TreeNode?): Int {
        val result = IntArray(1)
        helper(root, result)
        return result[0]
    }

    fun helper(node: TreeNode?, cnt: IntArray): Boolean {
        if(node == null) return true
        val leftNode = helper(node?.left, cnt)
        val rightNode = helper(node?.right, cnt)
        if(leftNode && rightNode &&
                (node?.left == null || node!!.`val` == node!!.left!!.`val`) &&
                (node?.right == null || node!!.`val` == node!!.right!!.`val`)) {
            cnt[0]++
            return true
        }
        return false
    }
}
