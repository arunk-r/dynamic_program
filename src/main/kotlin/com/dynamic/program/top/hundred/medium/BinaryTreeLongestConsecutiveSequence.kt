package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 298. Binary Tree Longest Consecutive Sequence
 * Medium
 * 1.1K
 * 235
 * company
 * Facebook
 * company
 * Amazon
 * company
 * Google
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 *
 * A consecutive sequence path is a path where the values increase by one along the path.
 *
 * Note that the path can start at any node in the tree, and you cannot go from a node to its parent in the path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 * Example 2:
 *
 *
 * Input: root = [2,null,3,2,null,1]
 * Output: 2
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -3 * 104 <= Node.val <= 3 * 104
 */
class BinaryTreeLongestConsecutiveSequence {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun longestConsecutive(root: TreeNode?): Int {
        return helper(root, 0, 0)
    }

    fun helper(node: TreeNode?, parent: Int, cnt: Int): Int {
        if (node == null) {
            return cnt
        } else {
            val newLength = if (node.`val` == parent + 1) cnt + 1 else 1
            return maxOf(newLength, maxOf(helper(node.left, node.`val`, newLength), helper(node.right, node.`val`, newLength)))
        }
    }
}
