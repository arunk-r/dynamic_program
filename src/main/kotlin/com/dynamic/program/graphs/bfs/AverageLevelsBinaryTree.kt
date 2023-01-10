package com.dynamic.program.graphs.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Average of Levels in Binary Tree
 *
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 *
 * Example 2:
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 */
class AverageLevelsBinaryTree {
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        val out = mutableListOf<Double>()
        val q = ArrayDeque<TreeNode>()
        root?.let{q.addLast(it)}
        while (q.isNotEmpty()) {
            val size = q.size
            var sum:Double = 0.0
            for (i in 0 until size) {
                val c = q.removeFirst()
                sum += c.`val`
                c.left?.let{q.addLast(it)}
                c.right?.let{q.addLast(it)}
            }
            out.add(sum/(size * 1.0))
        }
        return out.toDoubleArray()
    }
}