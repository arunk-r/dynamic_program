package com.dynamic.program.graphs.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Maximum Level Sum of a Binary Tree
 *
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 *
 * Example 1:
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 *
 * Example 2:
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -10^5 <= Node.val <= 10^5
 */
class MaximumLevelSumBinaryTree {
    var max = Int.MIN_VALUE
    var level = 0
    fun maxLevelSum(root: TreeNode?): Int {
        val q = ArrayDeque<TreeNode>()
        root?.let{q.addLast(it)}
        var l = 0
        while (q.isNotEmpty()) {
            var sum = 0
            val size = q.size
            for(i in 0 until size) {
                val c = q.removeFirst()
                sum += c.`val`

                c.left?.let{q.addLast(it)}
                c.right?.let{q.addLast(it)}
            }
            l++
            if (sum > max) {
                level = l
                max = sum
            }
        }
        return level
    }
}