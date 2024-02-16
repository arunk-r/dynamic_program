package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 549. Binary Tree Longest Consecutive Sequence II
 * Medium
 * 1.1K
 * 87
 * company
 * Google
 * Given the root of a binary tree, return the length of the longest consecutive path in the tree.
 *
 * A consecutive path is a path where the values of the consecutive nodes in the path differ by one. This path can be either increasing or decreasing.
 *
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
 * On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 * Example 2:
 *
 *
 * Input: root = [2,1,3]
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -3 * 104 <= Node.val <= 3 * 104
 */
class BinaryTreeLongestConsecutiveSequenceII {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var maxValue = 0
    fun longestConsecutive(root: TreeNode?): Int {
        dfs(root)
        return maxValue
    }

    fun dfs(node: TreeNode?): Pair<Int, Int> {
        if (node == null) return Pair(0, 0)

        var incr = 1
        var decr = 1
        if (node?.left != null) {
            val (l, r) = dfs(node?.left)
            if (node!!.`val` == node!!.left!!.`val` + 1) {
                incr = l + 1
            }
            if (node!!.`val` == node!!.left!!.`val` - 1) {
                decr = r + 1
            }
        }

        if (node?.right != null) {
            val (l, r) = dfs(node?.right)
            if (node!!.`val` == node!!.right!!.`val` + 1) {
                incr = maxOf(incr, l + 1)
            }
            if (node!!.`val` == node!!.right!!.`val` - 1) {
                decr = maxOf(decr, r + 1)
            }
        }
        maxValue = maxOf(maxValue, incr + decr - 1)
        return Pair(incr, decr)
    }
}