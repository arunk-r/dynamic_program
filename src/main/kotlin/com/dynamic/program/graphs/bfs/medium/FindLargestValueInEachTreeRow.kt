package com.dynamic.program.graphs.bfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * 515. Find Largest Value in Each Tree Row - https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
 * Medium
 * 2.7K
 * 95
 * company
 * Facebook
 * company
 * Amazon
 * company
 * Samsung
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,2,5,3,null,9]
 * Output: [1,3,9]
 * Example 2:
 *
 * Input: root = [1,2,3]
 * Output: [1,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [0, 104].
 * -231 <= Node.val <= 231 - 1
 */
class FindLargestValueInEachTreeRow {
    fun largestValues(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result
        val q = ArrayDeque<TreeNode>()
        q.addLast(root)
        while(q.isNotEmpty()) {
            var max = Int.MIN_VALUE
            for(i in q.size downTo 1) {
                val n = q.removeFirst()
                max = maxOf(max, n.`val`)
                n.left?.let{q.addLast(it)}
                n.right?.let{q.addLast(it)}
            }
            result.add(max)
        }
        return result
    }
}
