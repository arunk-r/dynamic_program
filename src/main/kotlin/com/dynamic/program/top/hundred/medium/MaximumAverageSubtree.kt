package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/maximum-average-subtree/
 *
 * 1120. Maximum Average Subtree
 * Medium
 * 775
 * 33
 * company
 * Amazon
 * Given the root of a binary tree, return the maximum average value of a subtree of that tree. Answers within 10-5 of the actual answer will be accepted.
 *
 * A subtree of a tree is any node of that tree plus all its descendants.
 *
 * The average value of a tree is the sum of its values, divided by the number of nodes.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have an average of 6 / 1 = 6.
 * For the node with value = 1 we have an average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 * Example 2:
 *
 * Input: root = [0,null,1]
 * Output: 1.00000
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 105
 */
class MaximumAverageSubtree {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maximumAverageSubtree(root: TreeNode?): Double {
        val result = DoubleArray(1)
        dfs(root, result)
        return result[0]
    }

    fun dfs(node: TreeNode?, max: DoubleArray): Pair<Int, Int> {
        if (node == null) {
            return Pair(0, 0)
        } else {
            val (leftSum, leftCnt) = dfs(node?.left, max)
            val (rightSum, rightCnt) = dfs(node?.right, max)
            val noOfNodesCnt = leftCnt + rightCnt + 1
            val totalSum = leftSum + rightSum + node!!.`val`

            max[0] = maxOf(max[0], (totalSum * 1.0) / (1.0 * noOfNodesCnt))
            return Pair(totalSum, noOfNodesCnt)
        }
    }
}
