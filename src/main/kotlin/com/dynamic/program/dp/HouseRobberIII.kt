package com.dynamic.program.dp

import com.dynamic.program.trees.TreeNode

/**
 * House Robber III
 *
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses
 * in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 *
 * Example 1:
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 *
 * Example 2:
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^4
 *
 */
class HouseRobberIII {
    private val robbed = hashMapOf<TreeNode, Int>()
    private val notRobbed = hashMapOf<TreeNode, Int>()

    fun rob(root: TreeNode?): Int {
        return dp(root, false)
    }

    private fun dp(root: TreeNode?, parentRobbed: Boolean): Int {
        if(root == null) return 0

        if(parentRobbed) {
            if(notRobbed.containsKey(root)) return notRobbed[root]!!

            val result = dp(root.left, false) + dp(root.right, false)
            notRobbed[root] = result
            return result
        } else {
            if(robbed.containsKey(root)) return robbed[root]!!

            val rob = root.`val` + dp(root.left, true) + dp(root.right, true)
            val not = dp(root.left, false) + dp(root.right, false)
            val result = maxOf(rob, not)
            robbed[root] = result
            return result
        }
    }
}